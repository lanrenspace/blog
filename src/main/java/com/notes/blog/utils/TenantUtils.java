package com.notes.blog.utils;

import com.notes.blog.exceptions.RequestNoPermissionException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Create by HeLongJun on 2021/7/20 16:53
 *
 * @author lanrenspace@163.com
 * @Description: 租户操作工具
 */
public class TenantUtils {

    /**
     * 获取租户编码
     *
     * @return
     */
    public static String getTenantCode() {
        return "10006";
    }

    /**
     * 请求令牌
     */
    private static final String REQUESTTOKENPARAM = "RequestToken";


    /**
     * 获取请求token
     *
     * @param request
     * @return
     */
    public static String validRequestToken(ServerRequest request) {
        List<String> tokens = request.headers().header(REQUESTTOKENPARAM);
        if (CollectionUtils.isEmpty(tokens)) {
            return null;
        }
        return tokens.get(0);
    }

    public static String valid(ServerRequest request) {
        String token = validRequestToken(request);
        if (token == null) {
            throw new RequestNoPermissionException("请求无权限!");
        }
        return token;
    }


    /**
     * 生成租户编号
     *
     * @return
     */
    public static String generateCode() {
        String sixChar = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        Date date = new Date();
        String time = sdf.format(date);
        for (int i = 0; i < time.length() / 2; i++) {
            String singleChar;
            String x = time.substring(i * 2, (i + 1) * 2);
            int b = Integer.parseInt(x);
            if (b < 10) {
                singleChar = Integer.toHexString(Integer.parseInt(x));
            } else if (b >= 10 && b < 36) {
                singleChar = String.valueOf((char) (Integer.parseInt(x) + 55));
            } else {
                singleChar = String.valueOf((char) (Integer.parseInt(x) + 61));
            }
            sixChar = sixChar + singleChar;

        }
        return sixChar;
    }
}
