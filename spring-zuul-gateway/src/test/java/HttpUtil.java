import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

@Slf4j
public class HttpUtil {

    /**
     * @param url   请求地址
     * @param query 参数内容
     * @return
     */
    public static String httpPost(String url, String query) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            System.out.println("url:" + url);
            System.out.println("param: " + query.toString());
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Length", String.valueOf(query.getBytes("UTF-8").length));
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            try {
                DataOutputStream output = new DataOutputStream(conn.getOutputStream());
            } catch (Exception e) {
                return result;
            }

            out = new PrintWriter(conn.getOutputStream());
            out.print(query);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return result;
            }
        }
        return result;
    }

    /**
     * @param url   请求地址
     * @param query 参数内容
     * @return
     */
    public static String httpZJPost(String url, String query) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            System.out.println("url:" + url);
            System.out.println("param: " + query.toString());
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Length", String.valueOf(query.getBytes("UTF-8").length));
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            conn.setConnectTimeout(5000000);
            conn.setReadTimeout(5000000);
            try {
                DataOutputStream output = new DataOutputStream(conn.getOutputStream());
            } catch (Exception e) {
                return result;
            }

            out = new PrintWriter(conn.getOutputStream());
            out.print(query);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return result;
            }
        }
        return result;
    }

    /**
     * 请求http接口
     *
     * @param url
     * @return
     */
    public static String openHttp(String url) {
        long t = System.currentTimeMillis();
        String str_return = "";
        try {
            URL console = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) console.openConnection();
            conn.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String input;
            while ((input = br.readLine()) != null) {
                str_return = str_return + input;
            }
            br.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str_return;
    }

    /***
     * 下载文件到本地
     * @param url 下载地址
     * @param descDir 目标目录
     * @return 文件保存路径
     */
    public static String downloadFromUrl(String url, String descDir) {
        String localFilePath = "";
        //下载文件到本地
        try {
            URL httpUrl = new URL(url);
            File dirFile = new File(descDir);
            if (!dirFile.exists()) {
                dirFile.mkdir();
            }
            localFilePath = descDir + url.substring(url.lastIndexOf(File.separator) + 1);
            File localFile = new File(localFilePath);
            //存在直接返回
            if (localFile.exists()) return localFilePath;
            FileUtils.copyURLToFile(httpUrl, new File(localFilePath));
        } catch (IOException e) {
            log.error("------------文件路径为{}------localFilePathHttpUtil.downloadFromUrl出现异常：--------",localFilePath, e);

        }
        return localFilePath;
    }

    //    /**
//     * 获取用户的展示IP地址
//     * @param httpRequest
//     * @return
//     */
//    public static String getIp(HttpServletRequest httpRequest) {
//        String ip = httpRequest.getHeader("x-forwarded-for");
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = httpRequest.getHeader("X-Forwarded-For");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = httpRequest.getHeader("Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = httpRequest.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = httpRequest.getRemoteAddr();
//        }
//        if (ip.contains(",")) {
//            String[] ipArray = ip.split(",");
//            for (String ipTmp : ipArray) {
//                if (!"unknown".equalsIgnoreCase(ipTmp)) {
//                    ip = ipTmp;
//                    break;
//                }
//            }
//        }
//        return ip;
//    }
    public static String doGet(String httpurl) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;// 返回结果字符串
        try {
            // 创建远程url连接对象
            URL url = new URL(httpurl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：6000毫秒
            connection.setConnectTimeout(6000);
            // 设置读取远程返回的数据时间：20000毫秒
            connection.setReadTimeout(20000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集

                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (Exception e) {
            log.error("HttpUtil doGet>>>>>>>>>>>>>>>>>" + httpurl, e);
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            connection.disconnect();// 关闭远程连接
        }

        return result;
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }

    /**
     * 上传永久素材
     *
     * @param file
     * @param title        type为video时需要,其他类型设null
     * @param introduction type为video时需要,其他类型设null
     * @return {"media_id":MEDIA_ID,"url":URL}
     */
    public static String uploadPermanentMaterial(String url, File file, String type, String title, String introduction) {

        String result = null;

        try {
            URL uploadURL = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) uploadURL.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Cache-Control", "no-cache");
            String boundary = "-----------------------------" + System.currentTimeMillis();
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            OutputStream output = conn.getOutputStream();
            output.write(("--" + boundary + "\r\n").getBytes());
            output.write(String.format("Content-Disposition: form-data; name=\"media\"; filename=\"%s\"\r\n", file.getName()).getBytes());
            output.write("Content-Type: video/mp4 \r\n\r\n".getBytes());

            byte[] data = new byte[1024];
            int len = 0;
            FileInputStream input = new FileInputStream(file);
            while ((len = input.read(data)) > -1) {
                output.write(data, 0, len);
            }

            /*对类型为video的素材进行特殊处理*/
            if ("video".equals(type)) {
                output.write(("--" + boundary + "\r\n").getBytes());
                output.write("Content-Disposition: form-data; name=\"description\";\r\n\r\n".getBytes());
                output.write(String.format("{\"title\":\"%s\", \"introduction\":\"%s\"}", title, introduction).getBytes());
            }

            output.write(("\r\n--" + boundary + "--\r\n\r\n").getBytes());
            output.flush();
            output.close();
            input.close();

            InputStream resp = conn.getInputStream();

            StringBuffer sb = new StringBuffer();

            while ((len = resp.read(data)) > -1)
                sb.append(new String(data, 0, len, "utf-8"));
            resp.close();
            result = sb.toString();
        } catch (IOException e) {
            //....
        }

        return result;
    }

    /**
     * @param url   请求地址
     * @param query 参数内容
     * @return
     */
    public static String httpWeixinPost(String url, String query) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            System.out.println("url:" + url);
            System.out.println("param: " + query.toString());
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Length", String.valueOf(query.getBytes("UTF-8").length));
            conn.setRequestProperty("Content-Type", "multipart/form-data");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            try {
                DataOutputStream output = new DataOutputStream(conn.getOutputStream());
            } catch (Exception e) {
                return result;
            }

            out = new PrintWriter(conn.getOutputStream());
            out.print(query);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return result;
            }
        }
        return result;
    }


    //模拟http请求

    /**
     * 模拟http请求
     *
     * @param url
     * @return
     */
    public static String getHttp(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

}
