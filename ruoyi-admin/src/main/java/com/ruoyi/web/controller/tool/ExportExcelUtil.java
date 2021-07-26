package com.ruoyi.web.controller.tool;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.CollectionUtils;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.exception.base.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

public class ExportExcelUtil {
    protected final Logger logger = LoggerFactory.getLogger(ExportExcelUtil.class);

    /**
     * 生成带数据的excel
     *
     * @param templateDetails
     * @param dataList
     * @param
     */
    public static AjaxResult generateExcel(List<Object> templateDetails, List<? extends BaseRowModel> dataList, HttpServletResponse response, HttpServletRequest request, String name) throws Exception {
        if (CollectionUtils.isEmpty(dataList)) {
            throw new BaseException("导出数据不能为空");
        }
        String sheetName = name;
        String filename = encodingFilename(sheetName);
        //初始化数据
        generateDatasExcel(templateDetails, dataList, filename);
        return AjaxResult.success(filename);
    }

    /**
     * 初始化数据
     *
     * @param templateDetails
     * @param dataList
     * @param path
     */
    private static void generateDatasExcel(List<Object> templateDetails, List<? extends BaseRowModel> dataList, String path) throws Exception {
        try (OutputStream out = new FileOutputStream(getAbsoluteFile(path))) {
            ExcelWriter excelWriter = EasyExcelFactory.getWriterWithTempAndHandler(null, out, ExcelTypeEnum.XLSX, true, null);
            Class<? extends BaseRowModel> a = dataList.get(0).getClass();
            Sheet sheet1 = new Sheet(1, 1, a);
            sheet1.setSheetName("用户数据");
            excelWriter.write(dataList, sheet1);
            excelWriter.finish();
        } catch (IOException e) {
        }
    }

    /**
     * 获取下载路径
     *
     * @param filename 文件名称
     */
    public static String getAbsoluteFile(String filename) {
        String downloadPath = Global.getDownloadPath() + filename;
        File desc = new File(downloadPath);
        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        return downloadPath;
    }

    /**
     * 编码文件名
     */
    public static String encodingFilename(String filename) {
        filename = UUID.randomUUID().toString() + "_" + filename + ".xlsx";
        return filename;
    }

}
