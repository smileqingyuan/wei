package win.smile.crawler;

import com.alibaba.fastjson.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.util.StringUtils;
import win.smile.model.VipDealerModel;
import win.smile.util.ExcelExportUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class VipDealerCrawlerServiceTest {

    /**
     * 太平洋省份城市信息
     */
    private static final String DEFAULT_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.84 Safari/537.36";


    @Test
    public void crawlerPcautoDealerOne() throws IOException {
        Connection.Response response = null;
        try {
//            response = getResponse("http://www.pcauto.com.cn/qcbj/zz/");
            response = getResponse("http://price.pcauto.com.cn/dealer/interface/6_0/dealer_recommend_4cheshi_json_v2.jsp?vip=1&rid=2");
        } catch (Exception e) {

        }
        System.out.println(response.body());
        Document doc = response.parse();
        Elements jxsList = doc.getElementsByClass("jxsList");

        Element element = jxsList.get(0);
        Elements jxsEles = element.getElementsByClass("jxs");
        jxsEles.forEach(jxsEle -> {
            Elements titEles = jxsEle.getElementsByClass("tit");
            String text = titEles.first().text();
            String tel = jxsEle.getElementsByClass("tel").first().text();
            VipDealerModel vipDealerModel = new VipDealerModel();
            vipDealerModel.setTelephone(tel);
            vipDealerModel.setDealerName(text.split(" ")[0]);
        });

        if (jxsList.size() >= 1) {
            Element element1 = jxsList.get(1);
            jxsEles = element1.getElementsByClass("jxs");
            jxsEles.forEach(jxsEle -> {
                Elements titEles = jxsEle.getElementsByClass("tit");
                String tel = jxsEle.getElementsByClass("tel").first().text();
                String text = titEles.first().text();
                VipDealerModel vipDealerModel = new VipDealerModel();
                vipDealerModel.setTelephone(tel);
                vipDealerModel.setDealerName(text.split(" ")[0]);
                try {
                    vipDealerModel.setAddress(text.split(" ")[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println(text);
                }
                vipDealerModel.setType("展厅");
            });
        }
    }

    @Test
    public void crawlerPCautoDealerHall() throws IOException {
        // 读入上面输出的文件
        JSONReader reader = new JSONReader(new FileReader("pcautoArea.json"));
        reader.startArray();
        List<VipDealerModel> vipDealerList = new ArrayList<>();
        while (reader.hasNext()) {

            JSONObject jb = reader.readObject(JSONObject.class);
            JSONArray cityArr = jb.getJSONArray("citys");
            String provinceName = jb.getString("pro");
            for (int i = 0; i < cityArr.size(); i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                JSONObject cityObj = cityArr.getJSONObject(i);
                String cityId = cityObj.getString("cityId");
                String cityName = cityObj.getString("name");
                String url = "http://price.pcauto.com.cn/dealer/interface/6_0/dealer_recommend_4cheshi_json_v2.jsp?vip=1&rid=" + cityId;
                Connection.Response response = null;
                try {
                    response = getResponse(url);
                } catch (Exception e) {
                    System.err.println(cityObj);
                    continue;
                }

                String body = response.body();
                if (body.contains("\"total\":0")) {
                    continue;
                }
                body = body.replace("list(", "");
                body = body.substring(0, body.length() - 2);
                JSONObject jsonObject = JSON.parseObject(body);
//                System.out.println(jsonObject);
                JSONArray resultArr = jsonObject.getJSONArray("result");
                for (int j = 0; j < resultArr.size(); j++) {
                    JSONObject dealerJb = resultArr.getJSONObject(j);
                    String dealerName = dealerJb.getString("shortName");
                    String tel = dealerJb.getString("poc400Phone");
                    String address = dealerJb.getString("areaName");
                    VipDealerModel vipDealerModel = new VipDealerModel();
                    vipDealerModel.setDealerName(dealerName);
                    vipDealerModel.setTelephone(tel);
                    vipDealerModel.setAddress(address);
                    vipDealerModel.setProviceName(provinceName);
                    vipDealerModel.setCityName(cityName);
                    vipDealerModel.setType("展厅");
                    vipDealerList.add(vipDealerModel);
                }
            }
        }
        reader.endArray();
        reader.close();

        ExcelExportUtil.exportToFile("vip_dealer_pcauto_zhanting.xls", vipDealerList);

    }


    @Test
    public void crawlerPcautoDealer() throws IOException {

        // 读入上面输出的文件
        JSONReader reader = new JSONReader(new FileReader("pcautoArea.json"));
        reader.startArray();
        List<VipDealerModel> vipDealerList = new ArrayList<>();
        while (reader.hasNext()) {

            JSONObject jb = reader.readObject(JSONObject.class);
            JSONArray cityArr = jb.getJSONArray("citys");
            String provinceName = jb.getString("pro");
            for (int i = 0; i < cityArr.size(); i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                JSONObject cityObj = cityArr.getJSONObject(i);
                if (StringUtils.isEmpty(cityObj.getString("siteUrl"))) {
                    continue;
                }
                Connection.Response response = null;
                try {
                    response = getResponse("http:" + cityObj.getString("siteUrl"));
                } catch (Exception e) {
                    System.err.println(cityObj);
                    continue;
                }
                Document doc = response.parse();
                Elements jxsList = doc.getElementsByClass("jxsList");
                if (jxsList.size() == 0) {
                    continue;
                }
                Element element = jxsList.get(0);
                Elements jxsEles = element.getElementsByClass("jxs");
                jxsEles.forEach(jxsEle -> {
                    Elements titEles = jxsEle.getElementsByClass("tit");
                    String text = titEles.first().text();
                    String tel = jxsEle.getElementsByClass("tel").first().text();
                    VipDealerModel vipDealerModel = new VipDealerModel();
                    vipDealerModel.setTelephone(tel);
                    vipDealerModel.setDealerName(text.split(" ")[0]);
                    try {
                        vipDealerModel.setAddress(text.split(" ")[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println(text);
                    }
                    vipDealerModel.setProviceName(provinceName);
                    vipDealerModel.setCityName(cityObj.getString("name"));
                    vipDealerModel.setType("4s店");
                    vipDealerList.add(vipDealerModel);
                });

                if (jxsList.size() >= 1) {
                    Element element1 = jxsList.get(1);
                    jxsEles = element1.getElementsByClass("jxs");
                    jxsEles.forEach(jxsEle -> {
                        Elements titEles = jxsEle.getElementsByClass("tit");
                        String tel = jxsEle.getElementsByClass("tel").first().text();
                        String text = titEles.first().text();
                        VipDealerModel vipDealerModel = new VipDealerModel();
                        vipDealerModel.setTelephone(tel);
                        vipDealerModel.setDealerName(text.split(" ")[0]);
                        try {
                            vipDealerModel.setAddress(text.split(" ")[1]);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.err.println(text);
                        }
                        vipDealerModel.setProviceName(provinceName);
                        vipDealerModel.setCityName(cityObj.getString("name"));
                        vipDealerModel.setType("展厅");
                        vipDealerList.add(vipDealerModel);
                    });
                }
            }
        }
        reader.endArray();
        reader.close();

        ExcelExportUtil.exportToFile("vip_dealer_pc.xls", vipDealerList);

    }

    @Test
    public void crawlerVipDealerXCar() throws IOException {

        JSONReader reader = new JSONReader(new FileReader("xcarArea.json"));
        reader.startArray();
        List<VipDealerModel> vipDealerModelList = new ArrayList<>();
        JSONWriter writer = new JSONWriter(new FileWriter("xcarVipDealer.json"));
        writer.startArray();

        while (reader.hasNext()) {
            JSONObject jb = reader.readObject(JSONObject.class);
            JSONArray cityArr = jb.getJSONArray("citiesInfo");
            String provinceName = jb.getString("provinceName");
            for (int i = 0; i < cityArr.size(); i++) {

                JSONObject jsonObject = cityArr.getJSONObject(i);
                jsonObject.getString("cityName");
                String url = "http://dealer.xcar.com.cn/" + jsonObject.getString("ciytId") + "/?type=1&page=1";
                Connection.Response response = getResponse(url);
                if (response == null) {
                    System.err.println(url);
                    continue;
                }
                Document doc = response.parse();
                Elements page_down = doc.getElementsByClass("page");
                Elements eles = doc.getElementsByClass("dlists_list");
                if (eles.size() == 0) {
                    continue;
                }
                Elements liEles = eles.first().getElementsByTag("li");
                liEles.forEach(ele -> {
                    if (ele.getElementsByClass("agency_vip").size() == 0) return;
                    String titles = ele.getElementsByTag("dt").select("a[title]").attr("title");
                    String phone = ele.getElementsByClass("phone").select("b[title]").text();
                    String address = ele.getElementsByClass("site").select("span[title]").attr("title");
                    VipDealerModel vipDealerModel = new VipDealerModel();
                    vipDealerModel.setCityName(jsonObject.getString("cityName"));
                    vipDealerModel.setProviceName(provinceName);
                    vipDealerModel.setType("4s店");
                    vipDealerModel.setAddress(address);
                    vipDealerModel.setTelephone(phone);
                    vipDealerModel.setDealerName(titles);
                    vipDealerModelList.add(vipDealerModel);
                    writer.writeValue(vipDealerModel);
                });

                if (page_down.size() > 0) {
                    //多页
                    int totalPage = Integer.valueOf(page_down.last().text());
                    for (int j = 2; j <= totalPage; j++) {

                        boolean flag = false;
                        String pageUrl = "http://dealer.xcar.com.cn/" + jsonObject.getString("ciytId") + "/?type=1&page=" + j;
                        response = getResponse(pageUrl);
                        if (response == null) {
                            System.err.println(pageUrl);
                            continue;
                        }
                        doc = response.parse();
                        eles = doc.getElementsByClass("dlists_list");
                        if (eles.size() == 0) {
                            continue;
                        }
                        liEles = eles.first().getElementsByTag("li");
                        for (Element ele : liEles) {
                            if (ele.getElementsByClass("agency_vip").size() == 0) {
                                //翻页到了非vip页面
                                flag = true;
                                break;
                            }
                            String titles = ele.getElementsByTag("dt").select("a[title]").attr("title");
                            String phone = ele.getElementsByClass("phone").select("b[title]").text();
                            String address = ele.getElementsByClass("site").select("span[title]").attr("title");
                            VipDealerModel vipDealerModel = new VipDealerModel();
                            vipDealerModel.setCityName(jsonObject.getString("cityName"));
                            vipDealerModel.setProviceName(provinceName);
                            vipDealerModel.setType("4s店");
                            vipDealerModel.setTelephone(phone);
                            vipDealerModel.setAddress(address);
                            vipDealerModel.setDealerName(titles);
                            vipDealerModelList.add(vipDealerModel);
                            writer.writeValue(vipDealerModel);
                        }
                        if (flag) {
                            break;
                        }
                    }
                }
            }
        }
        writer.endArray();
        writer.close();
        reader.endArray();
        reader.close();
        ExcelExportUtil.exportToFile("vip_dealer_xcar_zonghe.xls", vipDealerModelList);

    }

    @Test
    public void crawlerVipDealerZongheXCar() throws IOException {

        JSONReader reader = new JSONReader(new FileReader("xcarArea.json"));
        reader.startArray();
        List<VipDealerModel> vipDealerModelList = new ArrayList<>();
        JSONWriter writer = new JSONWriter(new FileWriter("xcarVipDealer.json"));
        writer.startArray();

        while (reader.hasNext()) {
            JSONObject jb = reader.readObject(JSONObject.class);
            JSONArray cityArr = jb.getJSONArray("citiesInfo");
            String provinceName = jb.getString("provinceName");
            for (int i = 0; i < cityArr.size(); i++) {

                JSONObject jsonObject = cityArr.getJSONObject(i);
                jsonObject.getString("cityName");
                String url = "http://dealer.xcar.com.cn/" + jsonObject.getString("ciytId") + "/?type=2&page=1";
                Connection.Response response = getResponse(url);
                if (response == null) {
                    System.err.println(url);
                    continue;
                }
                Document doc = response.parse();
                Elements page_down = doc.getElementsByClass("page");
                Element elementById = doc.getElementById("dlists_zh");
                if(elementById==null){
                    break;
                }
                Elements eles = elementById.getElementsByClass("dlists_list");
                Elements liEles = eles.first().getElementsByTag("li");
                if (liEles.size() == 0) {
                    break;
                }
                liEles.forEach(ele -> {
                    if (ele.getElementsByClass("agency_vip").size() == 0) return;
                    String titles = ele.getElementsByTag("dt").select("a[title]").attr("title");
                    String phone = ele.getElementsByClass("phone").select("b[title]").text();
                    String address = ele.getElementsByClass("site").select("span[title]").attr("title");
                    VipDealerModel vipDealerModel = new VipDealerModel();
                    vipDealerModel.setCityName(jsonObject.getString("cityName"));
                    vipDealerModel.setProviceName(provinceName);
                    vipDealerModel.setType("4s店");
                    vipDealerModel.setAddress(address);
                    vipDealerModel.setTelephone(phone);
                    vipDealerModel.setDealerName(titles);
                    vipDealerModelList.add(vipDealerModel);
                    writer.writeValue(vipDealerModel);
                });

                if (page_down.size() > 0) {
                    //多页
                    int totalPage = Integer.valueOf(page_down.last().text());
                    for (int j = 2; j <= totalPage; j++) {

                        boolean flag = false;
                        String pageUrl = "http://dealer.xcar.com.cn/" + jsonObject.getString("ciytId") + "/?type=2&page=" + j;
                        response = getResponse(pageUrl);
                        if (response == null) {
                            System.err.println(pageUrl);
                            continue;
                        }
                        doc = response.parse();
                        eles = doc.getElementsByClass("dlists_zh");
                        if (eles.size() == 0) {
                            break;
                        }
                        elementById = doc.getElementById("dlists_zh");
                        eles = elementById.getElementsByClass("dlists_list");
                        liEles = eles.first().getElementsByTag("li");
                        if (liEles.size() == 0) {
                            break;
                        }
                        for (Element ele : liEles) {
                            if (ele.getElementsByClass("agency_vip").size() == 0) {
                                //翻页到了非vip页面
                                flag = true;
                                break;
                            }
                            String titles = ele.getElementsByTag("dt").select("a[title]").attr("title");
                            String phone = ele.getElementsByClass("phone").select("b[title]").text();
                            String address = ele.getElementsByClass("site").select("span[title]").attr("title");
                            VipDealerModel vipDealerModel = new VipDealerModel();
                            vipDealerModel.setCityName(jsonObject.getString("cityName"));
                            vipDealerModel.setProviceName(provinceName);
                            vipDealerModel.setType("4s店");
                            vipDealerModel.setTelephone(phone);
                            vipDealerModel.setAddress(address);
                            vipDealerModel.setDealerName(titles);
                            vipDealerModelList.add(vipDealerModel);
                            writer.writeValue(vipDealerModel);
                        }
                        if (flag) {
                            break;
                        }
                    }
                }
            }
        }
        writer.endArray();
        writer.close();
        reader.endArray();
        reader.close();
        ExcelExportUtil.exportToFile("vip_dealer_xcar_zonghe.xls", vipDealerModelList);

    }


    @Test
    public void testXCarAreaDataInfo() throws IOException {
        getXCarAreaInfo();
    }

    /**
     * 获得爱卡地区信息
     *
     * @return
     */
    public List<Map<String, Object>> getXCarAreaInfo() throws IOException {
        List<Map<String, Object>> provinceList = new ArrayList<>();
        String provinStr = "<li value=\"24\" class=\"\"><cite>A</cite><a>安徽省</a></li>" +
                "<li value=\"1\" class=\"\"><cite>B</cite><a>北京市</a></li>" +
                "<li value=\"4\" class=\"\"><cite>C</cite><a>重庆市</a></li>" +
                "<li value=\"33\" class=\"\"><cite>F</cite><a>福建省</a></li>" +
                "<li value=\"19\" class=\"\"><cite>G</cite><a>贵州省</a></li>" +
                "<li value=\"12\" class=\"\"><cite>G</cite><a>甘肃省</a></li>" +
                "<li value=\"31\" class=\"\"><cite>G</cite><a>广西省</a></li>" +
                "<li value=\"30\" class=\"\"><cite>G</cite><a>广东省</a></li>" +
                "<li value=\"22\" class=\"\"><cite>H</cite><a>河南省</a></li>" +
                "<li value=\"8\" class=\"\"><cite>H</cite><a>河北省</a></li>" +
                "<li value=\"21\" class=\"\"><cite>H</cite><a>湖北省</a></li>" +
                "<li value=\"34\" class=\"\"><cite>H</cite><a>海南省</a></li>" +
                "<li value=\"5\" class=\"\"><cite>H</cite><a>黑龙江省</a></li>" +
                "<li value=\"20\" class=\"\"><cite>H</cite><a>湖南省</a></li>" +
                "<li value=\"25\" class=\"\"><cite>J</cite><a>江苏省</a></li>" +
                "<li value=\"32\" class=\"\"><cite>J</cite><a>江西省</a></li>" +
                "<li value=\"7\" class=\"\"><cite>J</cite><a>吉林省</a></li>" +
                "<li value=\"6\" class=\"\"><cite>L</cite><a>辽宁省</a></li>" +
                "<li value=\"13\" class=\"\"><cite>N</cite><a>宁夏省</a></li>" +
                "<li value=\"9\" class=\"\"><cite>N</cite><a>内蒙古</a></li>" +
                "<li value=\"16\" class=\"\"><cite>Q</cite><a>青海省</a></li>" +
                "<li value=\"23\" class=\"\"><cite>S</cite><a>山东省</a></li>" +
                "<li value=\"11\" class=\"\"><cite>S</cite><a>山西省</a></li>" +
                "<li value=\"17\" class=\"\"><cite>S</cite><a>四川省</a></li>" +
                "<li value=\"10\" class=\"\"><cite>S</cite><a>陕西省</a></li>" +
                "<li value=\"2\" class=\"\"><cite>S</cite><a>上海市</a></li>" +
                "<li value=\"3\" class=\"\"><cite>T</cite><a>天津市</a></li>" +
                "<li value=\"14\" class=\"\"><cite>X</cite><a>新疆省</a></li>" +
                "<li value=\"15\" class=\"\"><cite>X</cite><a>西藏省</a></li>" +
                "<li value=\"18\" class=\"\"><cite>Y</cite><a>云南省</a></li>" +
                "<li value=\"26\" class=\"\"><cite>Z</cite><a>浙江省</a></li>";
        Document doc = Jsoup.parse(provinStr);
        Elements lis = doc.select("li");
        for (Element li : lis) {
            String value = li.attr("value");
            String province = li.text();
            Map<String, Object> provinceMap = new HashMap<>();
            provinceMap.put("proviceId", value);
            provinceMap.put("provinceName", province.substring(1));
            List<Map<String, String>> citiesInfo = getCityInfo(value);
            provinceMap.put("citiesInfo", citiesInfo);
            provinceList.add(provinceMap);
        }
        /*将该文件写入到json中*/
        JSONWriter writer = new JSONWriter(new FileWriter("xcarArea.json"));
        writer.startArray();
        for (int i = 0; i < provinceList.size(); ++i) {
            writer.writeValue(provinceList.get(i));
        }
        writer.endArray();
        writer.close();

        return provinceList;
    }

    public List<Map<String, String>> getCityInfo(String provinceId) {

        List<Map<String, String>> cityList = new ArrayList<>();
        String url = "http://dealer.xcar.com.cn/dealerdp_index.php?r=dealers/Ajax/selectCity&province_id=" + provinceId + "&pbid=0";
        Connection.Response response = getResponse(url);
        Document doc = Jsoup.parse(response.body());
        Elements lis = doc.select("li");
        for (Element li : lis) {
            String id = li.attr("id");
            if (StringUtils.isEmpty(id)) {
                continue;
            }
            String name = li.attr("name");
            Map<String, String> cityMap = new HashMap<>();
            cityMap.put("ciytId", id);
            cityMap.put("cityName", name);
            cityList.add(cityMap);
        }
        return cityList;
    }


    @Test
    public void crawlerXCarDealer() throws IOException {

        for (int i = 1; i < 32; i++) {
            String url = "http://dealer.xcar.com.cn/d21_291/?type=1&page=" + i;
            Connection.Response response = getResponse(url);
            Document doc = response.parse();
            Elements eles = doc.getElementsByClass("dlists_list").first().getElementsByTag("li");
            eles.forEach(ele -> {
                if (ele.getElementsByClass("agency_vip").size() == 0) return;
                String titles = ele.getElementsByTag("dt").select("a[title]").attr("title");
                String phone = ele.getElementsByClass("phone").select("b[title]").text();
                String address = ele.getElementsByClass("site").select("span[title]").attr("title");
                System.out.println(titles + " ------ " + phone + "======" + address);
            });
        }

    }

    private Connection.Response getResponse(String url) {
        Connection connect = Jsoup.connect(url);
        Connection.Response response = null;
        for (int i = 0; i < 10; i++) {
            try {
                connect.url(url)
                        .ignoreContentType(true)
                        .method(Connection.Method.GET)
                        .timeout(10000)
                        .userAgent(DEFAULT_USER_AGENT)
                        .maxBodySize(0);
                response = connect.execute();
            } catch (Exception e) {
            }
            if (response != null) {
                break;
            }
        }


        return response;
    }

    @Test
    public void testHandleDealer() {
        String dealerStr = "";
        String[] split = dealerStr.split(";");
        for (String s : split) {
            System.out.println(s.split(" ------ ")[0]);
        }

        System.out.println();
        for (String s : split) {
            System.out.println(s.split(" ------ ")[1].split("======")[0]);
        }
        System.out.println();
        for (String s : split) {
            System.out.println(s.split(" ------ ")[1].split("======")[1]);
        }
    }


}
