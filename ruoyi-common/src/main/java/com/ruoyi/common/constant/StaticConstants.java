package com.ruoyi.common.constant;

public interface StaticConstants {

    /**
     * 平台之间调用的加密公钥
     */
    public static final String INNER_PLATFORM_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDKNDfyXjiE2gYcV/2u0rEJYmMMK7zfYNEjbxBhvvxo7pt1A3EHMaH+iiUFut6pP7W8GU+C9prCRYs8jAidc5QShGcmkGERibf6VtWkLpYst2Nrz9RFpu72dHf5kzkogjA0+dtaQ/7msi3+WFPcajQD2wJqPwuPsys8KFdhvVUAnQIDAQAB";

    /**
     * 平台之间调用的加密私钥
     */
    public static final String INNER_PLATFORM_PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMo0N/JeOITaBhxX/a7SsQliYwwrvN9g0SNvEGG+/Gjum3UDcQcxof6KJQW63qk/tbwZT4L2msJFizyMCJ1zlBKEZyaQYRGJt/pW1aQuliy3Y2vP1EWm7vZ0d/mTOSiCMDT521pD/uayLf5YU9xqNAPbAmo/C4+zKzwoV2G9VQCdAgMBAAECgYAPA2WMB1bm9lEx1Y6uZlJiwf1cr/qsE0I0AVEUENwo5l6Ah6riv9qpTZSGImPZ6TMTLkXrEWhYOFWoPzV8i1JZE7g2tUlayUuFbDqGVlcdhu2Gl6Lw8proDVB269o8SaI+TwP/Ktql9R2OXkaUdLAvkCUgy7gtb43YBqNNj8rzQQJBAPdVhImhlKkKz5vQEBfGg18J/5aBmnBY+TwdSZThEvJdlqlnRqx8HfE1bfw4Ua2tH0H+Ir4zXkTc7fopHZCZMXUCQQDRSecun39DdjRRmB65MbvBnnjk9gtod8GncHSiAWAraj5JmRqzuDF1w6pbowbURdqVnQJUSQiDtBM7XldfUUWJAkA59OsayScpuWPOsiGjQJw2IASpQvNqkh7NDFvarCchbfbI+W9hKbBmfkuoh5X0KZYig6emhCj53/9P2GSaJpulAkBcxC41qzrksytDk3Li0ZmQm+aoTisGVja/OghomsV+2OvdAYElchpyQZVsYtmvE3ts35hdtbWRcysnII0H0eVJAkButuITzT81k4/1d1t+gf6rRz/miJxH+TTUcyfkHTDJl2OiUvsQnaK3W93OHYg+Ab7/dPIM6lEr5zjaT++tPx6L";

    /**
     * 加款订单号前缀
     */
    public static final String PERFIX_REFUND = "R";

    /**
     * 减款订单号前缀
     */
    public static final String PERFIX_DEDUCT = "D";


    //alipay查询
    public static final String ALIPAY_IP_URL_KEY = "alipay_ip_port";
    public static final String ALIPAY_IP_URL_VALUE = "ip:port";
    public static final String ALIPAY_SERVICE_API_KEY = "alipay-api";
    public static final String ALIPAY_SERVICE_API_VALUE_1 = "account-api-add-account"; //开户
    public static final String ALIPAY_SERVICE_API_VALUE_2 = "account-api-audit-merchant-status";//修改商户状态
    public static final String ALIPAY_SERVICE_API_VALUE_3 = "account-api-amount"; //资金账户加减款


    //dealpay网关地址ip
    public static final String DEALPAY_IP_PORT = "dealpay_ip_port";
    //pay网关地址ip
    public static final String PAY_IP_PORT = "pay_ip_port";

}
