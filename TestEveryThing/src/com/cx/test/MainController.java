package com.cx.test;

import java.awt.geom.Point2D;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cx.test.entry.IndexArray;
import com.cx.test.entry.MapMask;
import com.cx.test.entry.MyX509TrustManager;
import com.cx.test.entry.ProductData;
import com.cx.test.proxy.NormalPerson;
import com.cx.test.proxy.Person;
import com.cx.test.proxy.StarPerson;
import com.cx.test.proxy.StarPersonProxy;
import com.cx.test.thunder.SortByTime;
import com.cx.test.thunder.ThunderData;
import com.cx.test.thunder.ThunderValue;
import com.cx.test.thunder.ThunderValueRel;
import com.cx.test.tool.DistributionClass;
import com.hw.commons.db.DBDataType;
import com.hw.commons.db.DBParameter;
import com.hw.commons.db.datatable.DataRow;
import com.hw.commons.db.datatable.DataTable;
import com.hw.commons.db.redis.RedisClient;
import com.hw.commons.gis.map.Feature;
import com.hw.commons.gis.map.JsonMap;
import com.hw.commons.lang.compress.zip.ZipUtils;
import com.hw.commons.lang.io.DirAndFile;
import com.hw.commons.lang.io.StreamReader;
import com.hw.commons.lang.io.StreamWriter;
import com.hw.commons.lang.type.TDateTime;
import com.hw.commons.lang.utils.Convert;
import com.hw.commons.lang.utils.MathTools;
import com.hw.commons.net.ftp.FtpClient;
import com.hw.commons.net.smb.SmbClient;
import com.hw.hwcdp.publiccode.global.SystemInit;
import com.hw.hwcdp.publiccode.log.SystemLog;
import com.hw.weather.nwp.data.micaps.Diamond11;
import com.hw.weather.nwp.data.micaps.Diamond4;
import com.hw.weather.nwp.data.micaps.NetGridSet;
import com.hw.weather.nwp.data.micaps.Impl.DiamondInOutImpl;
import com.hw.weather.nwp.helper.RedisClientHelper;
import com.hw.weather.nwp.helper.RedisClientPublicHelper;
import com.hw.weather.nwp.utils.DiamondHelper;
import com.hw.weather.nwp.utils.NetDataUtils;
import com.hw.weather.nwp.utils.NetGridAlgorithm;
import com.hw.weather.nwp.utils.NetGridFourArithmetic;
import com.hw.weather.swp.JxhYbStationData.TJxhYbStation;
import com.hw.weather.swp.Tools.TJxhYbStationOutput;
import com.hw.weather.zdz.data.mdfs.StationData;
import com.hw.weather.zdz.standard.AQIDataTools;
import com.hw.weather.zdz.standard.AQIDataTools.TAQIData;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import sun.misc.ProxyGenerator;

import org.apache.activemq.protobuf.BufferOutputStream;
import org.apache.commons.codec.binary.Base64;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Jedis;
import org.tukaani.xz.common.EncoderUtil;

public class MainController {

	Properties properties = null;

	public MainController() {
		SystemInit.init(MainController.class);
	}

	public static void main(String[] args) throws Exception {
		System.out.println(String.format("%03d", 3));
		MainController mainController = new MainController();
		mainController.loadConfig();
//		NetGridAlgorithm.convertGridDataToLonLat(descSet, descDiam, srcdata, srcLon, srcLat, range);
		// System.out.println(mainController.httpsRequest("https://data.whqx.online:10005//Weather//NWP?projectname=&calltype=&jsoncallback=&iquery=Stream.GetJxhByTypeCodeAndTimeRangeAndLocation|3|String;JXH|String;release_ur|Int32;24|DateTime;2019-10-12
		// 12:41:46|DateTime值;2019-10-13
		// 12:41:46|String;114.3|String;30.59","GET",null));
		// mainController.httpsRequest("https://qlfy.sdmsc.net//api//surface//getsurfelebytime//?times=20191010062000&adminCodes=371000&elements=Datetime,Station_Id_C,Lat,Lon,TEM,TEM_Max,TEM_Min,PRE_1h,WIN_D_Avg_1mi,WIN_S_Avg_1mi,WIN_D_S_Max,WIN_S_Max,WIN_D_INST_Max,WIN_S_Inst_Max,WIN_D_Avg_2mi,WIN_S_Avg_2mi,WIN_D_Avg_10mi,WIN_S_Avg_10mi,GST&key=6fde3f68eef29039827027264c08f5db","GET",null);
		 mainController.loadMicaps();
//		 mainController.unpressZip();
		// mainController.zipZDZ(SystemInit.jarCurDir+"201903031600_AIRTEMP_CURRENT");
		// mainController.testAqiCal();
		// mainController.testAqiCal();
		// mainController.createMaskFile();
		// mainController.createZDZData("H:\\Redis_New\\{yyyy}\\{MM}\\{dd}\\",new
		// TDateTime("20190716000000"),"TCC_AVE_20_20");
		// mainController.createZDZData("H:\\Redis_New\\{yyyy}\\{MM}\\{dd}\\",new
		// TDateTime("20190717000000"),"TCC_AVE_20_20");
//		 mainController.loadXyArea();
		// mainController.loadNc();
		// mainController.parseNewZDZ();
		// mainController.loadWx();
		// mainController.changeMaskLonLat();
//		 mainController.loadJXH();
		// mainController.paramThunderTxt();
//		mainController.pasePngToBase64();
//		mainController.saveZDZ();
		// proxyTest();
		// testParseRedisData();
//		 testGetDayList();
		 testNewWebservice();
//		 testStreamWebservice();
		// testWriteFile();
//		testSaveOracle();
//		testParseTxt();

	}

	private static void testParseTxt() {
		ProductData productData = new ProductData();
		String filePathh="H:\\20200516120000.txt";
		ArrayList<String> conList = DirAndFile.readFileByLines(filePathh, "UTF-8");
		DataTable dt = new DataTable();
		dt.columns.add("STATIONCODE", DBDataType.String);
		dt.columns.add("STATIONNAME", DBDataType.String);
		dt.columns.add("STATIONLON", DBDataType.Decimal);
		dt.columns.add("STATIONLAT", DBDataType.Decimal);
		dt.columns.add("STATIONHEIGHT", DBDataType.Decimal);
		dt.columns.add("RAIN_SUM_BEGINTIME", DBDataType.DateTime);
		dt.columns.add("RAIN_SUM_ENDTIME", DBDataType.DateTime);
		dt.columns.add("RAIN_SUM_VALUE", DBDataType.Decimal);
		
		for (int i = 0; i < conList.size(); i++) {
			DataRow dr = dt.newRow();		
			String[] conDataList = conList.get(i).split(" ",-1);
			if(conDataList.length<=1)
				continue;
			dr.setValue("STATIONCODE", conDataList[1]);
			dr.setValue("STATIONNAME", productData.getStationName(conDataList[1]));
			dr.setValue("STATIONLON", conDataList[3]);
			dr.setValue("STATIONLAT", conDataList[2]);
			dr.setValue("STATIONHEIGHT", conDataList[4]);
			dr.setValue("RAIN_SUM_VALUE", conDataList[5]);
			dr.setValue("RAIN_SUM_BEGINTIME", new TDateTime("20200516120000").addHours(-1));
			dr.setValue("RAIN_SUM_ENDTIME", new TDateTime("20200516120000"));
		}
		
		System.out.println(dt.toJsonNoColumn());
		
	}

	private static void testSaveOracle() {
		String ttt = DirAndFile.loadStrFromFile("H:\\ZDZ.txt");
		ttt=ZipUtils.zipStr(ttt);
		DirAndFile.saveStrToFile(ttt, SystemInit.jarCurDir+"sss.txt","UTF-8");
		
		ProductData productData = new ProductData();
		String stationCode="58847";
		TDateTime happentime = new TDateTime("20200212100000");
		String airtemp_current = "3.4";
		String airtemp_max="6.2";
		TDateTime airtemp_max_time=new TDateTime("20200212092033");
		String sql="select count(*)count from zdz_atmosphereelement where stationcode='"+stationCode+"' and "
				+ "happentime=to_date('"+happentime.toString()+"','yyyy-mm-dd hh24:mi:ss')";
		
		DataTable resultDt = productData.loadDataTable(sql, null);
		
		String insertSql = "insert into zdz_atmosphereelement(ID,StationCode,Happentime,airtemp_current,airtemp_max,airtemp_max_time) "
				+"values(zdz_atmosphereelement_seq.nextval,'"+stationCode+"',to_date('"+happentime.toString()+"','yyyy-mm-dd hh24:mi:ss'),?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'))";
		String upDateSql = "update zdz_atmosphereelement set "
				+"airtemp_current=?,"
				+"airtemp_max=?,"
				+"airtemp_max_time=? "
				+"where stationcode='"+stationCode+"' and happentime=to_date('"+happentime.toString()+"','yyyy-mm-dd hh24:mi:ss')";
		ArrayList<DBParameter> parameters = new ArrayList<DBParameter>();
		parameters.add(new DBParameter("airtemp_current", airtemp_current));
		parameters.add(new DBParameter("airtemp_max",  airtemp_max));
		parameters.add(new DBParameter("airtemp_max_time", airtemp_max_time.toString()));
		
		if(null!=resultDt&&resultDt.rows.size()>0)
		{
			if(Integer.parseInt(resultDt.rows.get(0).getStr("count".toUpperCase()))>0)
			{
				productData.exec(upDateSql, parameters);
			}
			else
				productData.exec(insertSql, parameters);
		}
		
		
		
	}

	private void saveZDZ() {
		String filePath = SystemInit.jarCurDir+"20191217000000(1).000";
		 StationData stationData = new StationData();
         stationData.loadFromFile(filePath);
         ProductData productData = new ProductData();
         ArrayList<StationData.SingleStationData> datas =stationData.getStationDatas();
         
	      String sql="";
	      sql="insert into ZDZ_ATMOSPHEREELEMENT (ID,DataSource,Happentime,StationCode,airtemp_current) values(ZDZ_ATMOSPHEREELEMENT_SEQ.nextval,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?);";
	      sql+="insert into ZDZ_ATMOSPHEREELEMENT (ID,DataSource,Happentime,StationCode,rain_onehour) values(ZDZ_ATMOSPHEREELEMENT_SEQ.nextval,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?);";
	      ArrayList<DBParameter> parameters = new ArrayList<DBParameter>();
	      parameters.add(new DBParameter("?", "1"));
	      parameters.add(new DBParameter("?", TDateTime.now().toString()));
	      parameters.add(new DBParameter("?", "A1"));
	      parameters.add(new DBParameter("?", "20"));
	      
	      parameters.add(new DBParameter("?", "2"));
	      parameters.add(new DBParameter("?", TDateTime.now().toString()));
	      parameters.add(new DBParameter("?", "A2"));
	      parameters.add(new DBParameter("?", "21"));
	      sql="begin "+sql+"end;";
	      productData.exec(sql, parameters);
	      
	      
	}

	private void pasePngToBase64() {
		String filepath = "H:\\微信图片_20191203171228.png";
		try {
			
			FileInputStream sr = new FileInputStream(filepath);
			int size = (int) new File(filepath).length();

			String base64 = "";
			byte[] buff = new byte[size];
			sr.read(buff);
			base64 += Base64.encodeBase64String(buff);

			System.out.println(base64);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void parseNewZDZ() {
		String filepath = "H:\\新自动站格式样例\\Z_SURF_C_BEXA_20191201174725_O_AWS-MM_FTM_PQC.BIN";
		StreamReader sr = new StreamReader(filepath);
		System.out.println(new String(sr.readByteArray(8)));
		System.out.println("end.");
	}

	/*
	 * 处理https GET/POST请求 请求地址、请求方法、参数
	 */
	public String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		StringBuffer buffer = null;
		try {
			requestUrl = requestUrl.replace(" ", "%20");
			// 创建SSLContext
			SSLContext sslContext = SSLContext.getInstance("SSL");
			TrustManager[] tm = { new MyX509TrustManager() };
			// 初始化
			sslContext.init(null, tm, new java.security.SecureRandom());
			;
			// 获取SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod(requestMethod);
			// 设置当前实例使用的SSLSoctetFactory
			conn.setSSLSocketFactory(ssf);
			conn.connect();
			// 往服务器端写内容
			if (null != outputStr) {
				OutputStream os = conn.getOutputStream();
				os.write(outputStr.getBytes("utf-8"));
				os.close();
			}
			// 读取服务器端返回的内容
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			buffer = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	/**
	 * 解压内存库文件
	 */
	private void unpressZip() {
		
//		String ss = "19810405,11.8,,,,,14,19810405000000,9.8,19810405000000,12.7,10.4,2,19810405000000,,,,,,,,,1006.4,1007.3,1004.7,1.5,0,0.3,5.2,19810405000000,0.3,3.7,19810405000000,0,19810405000000,91,77,19810405000000,52.5,,,";
//		String[] ss2 = ss.split(",",-1);
//		System.out.println(ss2[26]);
		
		String filePath = SystemInit.jarCurDir + "01_HIS_EVA_AVE_20_20";
		try {
			System.out.println(
					new String(ZipUtils.unZipStr(DirAndFile.loadStrFromFile(filePath, "GBK")).getBytes("UTF-8")));
			DirAndFile.saveStrToFile(new String(ZipUtils.unZipStr(DirAndFile.loadStrFromFile(filePath, "GBK")).getBytes("UTF-8")), SystemInit.jarCurDir+"test.txt","UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 判断文件是否是11类还是4类
	 */
	private void loadMicaps() {
		
		String filePath="E:\\temp\\grads\\RAINNC\\999\\2020\\08\\04\\";
		ArrayList<NetGridSet> list = new ArrayList<NetGridSet>();
		for (int i = 1; i < 7; i++) {
			list.add((NetGridSet) DiamondHelper.diamonCreateFromFile(filePath+"20080412."+String.format("%03d", i)));
			
		}
		
		NetGridSet dSum =  NetGridAlgorithm.gridSetRatioSum(list, 1);
		DiamondHelper.diamondWriteTxtFile((DiamondInOutImpl) dSum, SystemInit.jarCurDir+"ttt.txt");

	}

	private void zipZDZ(String filepath) {
		String stationStr = ZipUtils.unZipStr(DirAndFile.loadStrFromFile(filepath));
		System.out.println(stationStr);
	}

	/**
	 * aqi算法确认
	 */
	private void testAqiCal() {
		AQIDataTools aqiTool = new AQIDataTools();
		// TAQIData tAqiData = aqiTool.getAQI(42f, 52f, 4f, 16f, 0.9*1000, 58f,
		// 72f);
		// System.out.println(tAqiData.aqi);
		//
		// TAQIData tAqiData1 = aqiTool.getAQI(27f, 42f, 6f, 13f, 0.7*1000, 86f,
		// 0f);
		// System.out.println(tAqiData1.aqi);

		TAQIData tAqiData2 = aqiTool.GetAQI_24(35.45f, 73.125f, 6.875f, 24.2f, 0.78 * 1000, 184f, 159f);
		System.out.println(tAqiData2.aqi);

	}

	/**
	 * 生成掩码文件(用于漯河)
	 */
	private void createMaskFile() {
		String mapFile = properties.getProperty("mapFile");
		ArrayList<MapMask> mapMaskList = new ArrayList<MapMask>();
		JsonMap jsonMap = JSON.parseObject(DirAndFile.loadStrFromFile(mapFile, "UTF-8"), JsonMap.class);
		// 格点文件路径
		String gdFile = properties.getProperty("gdFile");
		NetGridSet data = (NetGridSet) DiamondHelper.diamonCreateFromFile(gdFile);
		List<Feature> features = jsonMap.getFeatures();
		for (int i = 0; i < features.size(); i++) {
			Object[][] jsonArealist = features.get(i).getGeometry().getCoordinates();
			String areaname = features.get(i).getProperties().getName();
			String citycode = features.get(i).getProperties().getAreacode();
			String provincename = features.get(i).getProperties().getProvince();

			ArrayList<ArrayList<Point2D.Float>> area = new ArrayList<ArrayList<Point2D.Float>>();
			for (int j = 0; j < jsonArealist.length; j++) {
				ArrayList<Point2D.Float> carea = new ArrayList<Point2D.Float>();
				// ??????
				Object[] farealist = jsonArealist[j];
				for (int k = 0; k < farealist.length; k++) {
					JSONArray lonlat = JSON.parseArray(String.valueOf(farealist[k]));
					float lon;
					float lat;
					try {
						lon = lonlat.getFloatValue(0);
						lat = lonlat.getFloatValue(1);
						Point2D.Float point = new Point2D.Float();
						point.setLocation(lon, lat);
						carea.add(point);
					} catch (Exception e) {
						System.out.println(areaname + "数据异常------开始嵌套遍历");
						for (int l = 0; l < lonlat.size(); l++) {
							lon = lonlat.getJSONArray(l).getFloat(0);
							lat = lonlat.getJSONArray(l).getFloat(1);
							Point2D.Float point = new Point2D.Float();
							point.setLocation(lon, lat);
							carea.add(point);
						}

					}
				}

				area.add(carea);

			}
			MapMask mapMask = new MapMask();
			mapMask.areaCode = features.get(i).getProperties().getAreacode();
			mapMask.areaName = features.get(i).getProperties().getName();
			if (data instanceof Diamond4) {
				// 判断点在区域内
				for (int x = 0; x < data.getLonNum(); x++) {
					for (int y = 0; y < data.getLatNum(); y++) {
						for (int k = 0; k < area.size(); k++) {
							Point2D.Float point = new Point2D.Float();
							point.setLocation(data.getLon(x), data.getLat(y));
							if (MathTools.pointInFeaces(point, area.get(k)) || area.indexOf(area.get(k)) >= 0) {
								if (mapMask.areaName.equals("召陵区"))
									System.out.println(data.getLon(x) + "," + data.getLat(y));
								mapMask.indexArray.add(new IndexArray(data.getLon(x), data.getLat(y)));
							}
						}
					}
				}

			}
			mapMaskList.add(mapMask);

		}

		String content = JSON.toJSONString(mapMaskList);
		// 输出掩码文件
		DirAndFile.saveStrToFile(content, SystemInit.jarCurDir + "maskMap.json", "GBK");

	}

	private void createZDZData(String outDir, TDateTime time, String keyName) {
		String data = "";
		outDir = time.stringFormat(outDir);
		String fileName = time.toString("yyyyMMdd") + "_" + keyName;
		ProductData productData = new ProductData();
		DataTable dt = productData.loadDtInfo("hangzhou_all");
		for (int i = 0; i < dt.rows.size(); i++) {
			// data +=dt.rows.get(i).getStr("STATIONCODE")+","+new
			// Random().nextInt(8)+"-2"+","+new Random().nextInt(20)+"\n";
			data += dt.rows.get(i).getStr("STATIONCODE") + "," + new Random().nextInt(1000) + ","
					+ time.toString("yyyyMMdd") + String.format("%02d", new Random().nextInt(23)) + "00\n";
		}
		DirAndFile.saveStrToFile(ZipUtils.zipStr(data), outDir + fileName, "UTF-8");

	}

	private void loadConfig() throws Exception {
		properties = new Properties();
		String filePath = SystemInit.jarCurDir + "config.properties";
		properties.load(new FileInputStream(new File(filePath)));

	}

	/**
	 * 咸阳责任区，防御区，警戒区站点数据预处理
	 */
	private void loadXyArea() {
		ProductData producdata = new ProductData();

		String zrCityLinePath = properties.getProperty("zrCityLinePath");
		String jjCityLinePath = properties.getProperty("jjCityLinePath");
		String fyCityLinePath = properties.getProperty("fyCityLinePath");

		String zrCityLineContent = DirAndFile.loadStrFromFile(zrCityLinePath, "UTF-8");
		String jjCityLineContent = DirAndFile.loadStrFromFile(jjCityLinePath, "UTF-8");
		String fyCityLineContent = DirAndFile.loadStrFromFile(fyCityLinePath, "UTF-8");

		JsonMap zrcountyLineMap = JSON.parseObject(zrCityLineContent.toString(), JsonMap.class);
		JsonMap jjcountyLineMap = JSON.parseObject(jjCityLineContent.toString(), JsonMap.class);
		JsonMap fycountyLineMap = JSON.parseObject(fyCityLineContent.toString(), JsonMap.class);

		DataTable staDt = producdata.loadXyDtInfo("xianyan_area_all");
		String resultpath = SystemInit.jarCurDir + "StationInfo_xianyan_area_all.txt";
		DirAndFile.deleteFile(SystemInit.jarCurDir + "StationInfo_xianyan_area_all.txt");
		List<Feature> zrfeatures = zrcountyLineMap.getFeatures();
		List<Feature> jjfeatures = jjcountyLineMap.getFeatures();
		List<Feature> fyfeatures = fycountyLineMap.getFeatures();
		for (int i = 0; i < staDt.rows.size(); i++) {
			Point2D.Float point = new Point2D.Float();
			point.setLocation(java.lang.Float.parseFloat(staDt.rows.get(i).getStr("LONGITUDE")),
					java.lang.Float.parseFloat(staDt.rows.get(i).getStr("LATITUDE")));
			ArrayList<Point2D.Float> fyarea = new ArrayList<Point2D.Float>();
			for (int j = 0; j < fyfeatures.size(); j++) {
				Object[][] jsonArealist = fyfeatures.get(i).getGeometry().getCoordinates();
				for (int k = 0; k < jsonArealist.length; k++) {

					Object[] farealist = jsonArealist[k];
					for (int l = 0; l < farealist.length; l++) {
						JSONArray lonlat = JSON.parseArray(String.valueOf(farealist[l]));
						float lon;
						float lat;
						try {
							lon = lonlat.getFloatValue(0);
							lat = lonlat.getFloatValue(1);
							Point2D.Float point1 = new Point2D.Float(lon, lat);
							fyarea.add(point1);
						} catch (Exception e) {
							for (int l2 = 0; l2 < lonlat.size(); l2++) {
								lon = lonlat.getJSONArray(l2).getFloat(0);
								lat = lonlat.getJSONArray(l2).getFloat(1);
								Point2D.Float point1 = new Point2D.Float(lon, lat);
								fyarea.add(point1);
							}

						}
					}

				}

			}

			if (MathTools.pointInFeaces(point, fyarea)) {
				// 如果在防御区不在警戒区的，站点就是在防御区了
				ArrayList<Point2D.Float> jjarea = new ArrayList<Point2D.Float>();
				for (int j = 0; j < jjfeatures.size(); j++) {
					Object[][] jsonArealist = jjfeatures.get(i).getGeometry().getCoordinates();
					for (int k = 0; k < jsonArealist.length; k++) {

						Object[] farealist = jsonArealist[k];
						for (int l = 0; l < farealist.length; l++) {
							JSONArray lonlat = JSON.parseArray(String.valueOf(farealist[l]));
							float lon;
							float lat;
							try {
								lon = lonlat.getFloatValue(0);
								lat = lonlat.getFloatValue(1);
								Point2D.Float point1 = new Point2D.Float(lon, lat);
								jjarea.add(point1);
							} catch (Exception e) {
								for (int l2 = 0; l2 < lonlat.size(); l2++) {
									lon = lonlat.getJSONArray(l2).getFloat(0);
									lat = lonlat.getJSONArray(l2).getFloat(1);
									Point2D.Float point1 = new Point2D.Float(lon, lat);
									jjarea.add(point1);
								}

							}
						}

					}

				}
				if (!MathTools.pointInFeaces(point, jjarea)) {
					String stationelement = staDt.rows.get(i).getStr("STATIONELEMENT").replace(",", "-");
					String stationtype = staDt.rows.get(i).getStr("STATIONTYPE").replace(",", "-");
					String content = staDt.rows.get(i).getStr("STATIONCODE") + ","
							+ staDt.rows.get(i).getStr("STATIONNAME") + ","
							+ staDt.rows.get(i).getStr("STATIONNAME_PINYIN") + "," + stationtype + ","
							+ staDt.rows.get(i).getStr("LONGITUDE") + "," + staDt.rows.get(i).getStr("LATITUDE") + ","
							+ staDt.rows.get(i).getStr("LONGITUDE_CORRECTION") + ","
							+ staDt.rows.get(i).getStr("LATITUDE_CORRECTION") + "," + staDt.rows.get(i).getStr("HEIGHT")
							+ "," + staDt.rows.get(i).getStr("ELEMENTCOUNT") + "," + staDt.rows.get(i).getStr("COUNTRY")
							+ "," + staDt.rows.get(i).getStr("PROVINCE") + "," + staDt.rows.get(i).getStr("CITY") + ","
							+ staDt.rows.get(i).getStr("COUNTY") + "," + staDt.rows.get(i).getStr("TOWN") + ","
							+ staDt.rows.get(i).getStr("VILLAGE") + "," + staDt.rows.get(i).getStr("STATIONLEVEL_TYPE")
							+ "," + staDt.rows.get(i).getStr("STATIONLEVEL_XZ") + ","
							+ staDt.rows.get(i).getStr("ISEXCLUDE") + "," + stationelement + ","
							+ staDt.rows.get(i).getStr("PROVINCECODE") + "," + staDt.rows.get(i).getStr("CITYCODE")
							+ "," + staDt.rows.get(i).getStr("COUNTYCODE") + ",fyq" + "\n";
					DirAndFile.addStrToFile(content, resultpath);
				}
				// 如果在警戒区里面，就判断是否在责任区里面，在的话就是责任区的了
				else {
					// 如果在警戒区里面并且不再责任区的，就是在警戒区了
					ArrayList<Point2D.Float> zrarea = new ArrayList<Point2D.Float>();
					for (int j = 0; j < zrfeatures.size(); j++) {
						Object[][] jsonArealist = zrfeatures.get(i).getGeometry().getCoordinates();
						for (int k = 0; k < jsonArealist.length; k++) {

							Object[] farealist = jsonArealist[k];
							for (int l = 0; l < farealist.length; l++) {
								JSONArray lonlat = JSON.parseArray(String.valueOf(farealist[l]));
								float lon;
								float lat;
								try {
									lon = lonlat.getFloatValue(0);
									lat = lonlat.getFloatValue(1);
									Point2D.Float point1 = new Point2D.Float(lon, lat);
									zrarea.add(point1);
								} catch (Exception e) {
									for (int l2 = 0; l2 < lonlat.size(); l2++) {
										lon = lonlat.getJSONArray(l2).getFloat(0);
										lat = lonlat.getJSONArray(l2).getFloat(1);
										Point2D.Float point1 = new Point2D.Float(lon, lat);
										zrarea.add(point1);
									}

								}
							}

						}

					}
					if (!MathTools.pointInFeaces(point, zrarea)) {
						String stationelement = staDt.rows.get(i).getStr("STATIONELEMENT").replace(",", "-");
						String stationtype = staDt.rows.get(i).getStr("STATIONTYPE").replace(",", "-");
						String content = staDt.rows.get(i).getStr("STATIONCODE") + ","
								+ staDt.rows.get(i).getStr("STATIONNAME") + ","
								+ staDt.rows.get(i).getStr("STATIONNAME_PINYIN") + "," + stationtype + ","
								+ staDt.rows.get(i).getStr("LONGITUDE") + "," + staDt.rows.get(i).getStr("LATITUDE")
								+ "," + staDt.rows.get(i).getStr("LONGITUDE_CORRECTION") + ","
								+ staDt.rows.get(i).getStr("LATITUDE_CORRECTION") + ","
								+ staDt.rows.get(i).getStr("HEIGHT") + "," + staDt.rows.get(i).getStr("ELEMENTCOUNT")
								+ "," + staDt.rows.get(i).getStr("COUNTRY") + "," + staDt.rows.get(i).getStr("PROVINCE")
								+ "," + staDt.rows.get(i).getStr("CITY") + "," + staDt.rows.get(i).getStr("COUNTY")
								+ "," + staDt.rows.get(i).getStr("TOWN") + "," + staDt.rows.get(i).getStr("VILLAGE")
								+ "," + staDt.rows.get(i).getStr("STATIONLEVEL_TYPE") + ","
								+ staDt.rows.get(i).getStr("STATIONLEVEL_XZ") + ","
								+ staDt.rows.get(i).getStr("ISEXCLUDE") + "," + stationelement + ","
								+ staDt.rows.get(i).getStr("PROVINCECODE") + "," + staDt.rows.get(i).getStr("CITYCODE")
								+ "," + staDt.rows.get(i).getStr("COUNTYCODE") + ",jjq" + "\n";
						DirAndFile.addStrToFile(content, resultpath);
					} else {
						String stationelement = staDt.rows.get(i).getStr("STATIONELEMENT").replace(",", "-");
						String stationtype = staDt.rows.get(i).getStr("STATIONTYPE").replace(",", "-");
						String content = staDt.rows.get(i).getStr("STATIONCODE") + ","
								+ staDt.rows.get(i).getStr("STATIONNAME") + ","
								+ staDt.rows.get(i).getStr("STATIONNAME_PINYIN") + "," + stationtype + ","
								+ staDt.rows.get(i).getStr("LONGITUDE") + "," + staDt.rows.get(i).getStr("LATITUDE")
								+ "," + staDt.rows.get(i).getStr("LONGITUDE_CORRECTION") + ","
								+ staDt.rows.get(i).getStr("LATITUDE_CORRECTION") + ","
								+ staDt.rows.get(i).getStr("HEIGHT") + "," + staDt.rows.get(i).getStr("ELEMENTCOUNT")
								+ "," + staDt.rows.get(i).getStr("COUNTRY") + "," + staDt.rows.get(i).getStr("PROVINCE")
								+ "," + staDt.rows.get(i).getStr("CITY") + "," + staDt.rows.get(i).getStr("COUNTY")
								+ "," + staDt.rows.get(i).getStr("TOWN") + "," + staDt.rows.get(i).getStr("VILLAGE")
								+ "," + staDt.rows.get(i).getStr("STATIONLEVEL_TYPE") + ","
								+ staDt.rows.get(i).getStr("STATIONLEVEL_XZ") + ","
								+ staDt.rows.get(i).getStr("ISEXCLUDE") + "," + stationelement + ","
								+ staDt.rows.get(i).getStr("PROVINCECODE") + "," + staDt.rows.get(i).getStr("CITYCODE")
								+ "," + staDt.rows.get(i).getStr("COUNTYCODE") + ",zrq" + "\n";
						DirAndFile.addStrToFile(content, resultpath);
					}

				}

			}
		}

	}

	private void loadNc() {
		String path = "H:\\19112820.048";

		Diamond4 data = (Diamond4) DiamondHelper.diamonCreateFromFile(path);
		System.out.println(data.toString());

	}

	// �޸ĺ��ε�4���ļ�
	private void changeMaskLonLat() {
		String filepath = "H:\\HSuf.txt";

		Diamond4 srcd4 = (Diamond4) DiamondHelper.diamonCreateFromFile(filepath);

		NetGridAlgorithm.calcGridNearestPoint(srcd4, 107.5f, 33.55f, 109.95f, 34.9f, 0, 0, srcd4.getLonInterval(),
				srcd4.getLatInterval());
		DiamondHelper.diamondWriteTxtFile(srcd4, "H:\\HSuf_XiAn.txt");

	}

	private void loadJXH() {
//		String filepath = "H:\\o_f_bfze_city_2020071020_Data";
//		String filepath = SystemInit.jarCurDir+"o_f_bfze_city_2020071020_Data";
//		TJxhYbStation jxh = new TJxhYbStation();
////		
//		jxh.readFromFile(filepath);
	
		try {
//			TJxhYbStation jxh = RedisClientPublicHelper.redisClient(0).getjedis_DataToJxh("o_f_bfze_city_2020071020_Data");
			TJxhYbStation jxh = new TJxhYbStation();
			RedisClient redisClient = new RedisClient("10.203.8.5",6379);
			DirAndFile.saveBytesToFile(redisClient.getValueToArray("o_f_bfze_city_2020071020_Data"), SystemInit.jarCurDir+"test.txt");
			
//			jxh.readFromStream(redisClient.getValueToArray("o_f_bfze_city_2020071020_Data"));
			StreamReader sr = new StreamReader(redisClient.getValueToArray("o_f_bfze_city_2020071020_Data"));
			jxh.readHeaderFromTxtFile(sr);
			
			for (int k = 0; k < jxh.getStacount(); ++k) {
				System.out.println(sr.readOneTonken());
				System.out.println(sr.readOneTonkenToFloat());
				System.out.println(sr.readOneTonkenToFloat());
				System.out.println(sr.readOneTonkenToFloat());
				System.out.println(sr.readOneTonkenToInt());
				System.out.println(sr.readOneTonkenToInt());
				
			

				for (int i = 0; i < 14; ++i) {
					System.out.println(sr.readOneTonkenToInt());
					

					for (int j = 0; j < 21; ++j) {
					
						System.out.println(sr.readOneTonkenToFloat());
					}
				}

		
			}

			
	
			
//			TJxhYbStationOutput jxhout = new TJxhYbStationOutput();
//			StringBuilder sb = null;
//			sb = jxhout.writeToFileByStringBulid(jxh, 0);
//			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		

	}

	
	public void distrutest() throws Exception {
		String realName = "1.txt";
		String localPath = SystemInit.jarCurDir + "1.txt";
		String content = DirAndFile.loadStrFromFile("H:\\test.txt", "UTF-8");

		if (content != null) {
			DistributionClass distributionClass = JSON.parseObject(content, DistributionClass.class);
			if (distributionClass.getData().get(0).getFtp() != null) {
				for (int i = 0; i < distributionClass.getData().get(0).getFtp().size(); i++) {

					FtpClient ftpClient = new FtpClient(
							distributionClass.getData().get(0).getFtp().get(i).getFtpserver(),
							distributionClass.getData().get(0).getFtp().get(i).getFtpport(), true,
							distributionClass.getData().get(0).getFtp().get(i).getFtpname(),
							distributionClass.getData().get(0).getFtp().get(i).getFtppassword());
					if (ftpClient != null) {
						// �Ȳ��Ƕ�׽����ļ���
						String[] fileDirList = DirAndFile.dirWithSeparator(
								distributionClass.getData().get(0).getFtp().get(i).getFtpfolder(), "/").split("/", -1);
						String fileDir = fileDirList[0];
						for (int j = 1; j < fileDirList.length; j++) {
							fileDir = fileDir + "/" + fileDirList[j];
						}

						boolean result = ftpClient.putFile(DirAndFile.dirWithSeparator(fileDir, "/") + realName,
								localPath);
						ftpClient.close();
						if (result)
							SystemLog.info("Save ftp " + ftpClient.ftpServer + " success.");
						else
							SystemLog.info("Save error " + ftpClient.ftpServer + " success.");
					}

				}
			}

			if (distributionClass.getData().get(0).getLocalpath() != null) {
				for (int i = 0; i < distributionClass.getData().get(0).getLocalpath().size(); i++) {

					if (distributionClass.getData().get(0).getLocalpath().get(i).getLocalpath().indexOf("\\\\") < 0) {

						DirAndFile.checkDir(distributionClass.getData().get(0).getLocalpath().get(i).getLocalpath());
						DirAndFile.copyFile(localPath,
								DirAndFile.dirWithSeparator(
										distributionClass.getData().get(0).getLocalpath().get(i).getLocalpath())
										+ realName);

					} else {
						SmbClient smbClient = new SmbClient(
								distributionClass.getData().get(0).getLocalpath().get(i).getLocalpathusername(),
								distributionClass.getData().get(0).getLocalpath().get(i).getLocalpathpassword());
						if (smbClient != null) {
							// �Ƚ����ļ���
							smbClient
									.createDir(distributionClass.getData().get(0).getLocalpath().get(i).getLocalpath());

							boolean result = smbClient.putFile(localPath,
									DirAndFile.dirWithSeparator(
											distributionClass.getData().get(0).getLocalpath().get(i).getLocalpath())
											+ realName);

							if (result)
								SystemLog.info("Save smb "
										+ distributionClass.getData().get(0).getLocalpath().get(i).getLocalpath()
										+ realName + " success.");
							else
								SystemLog.info("Save error "
										+ distributionClass.getData().get(0).getLocalpath().get(i).getLocalpath()
										+ realName + " success.");
						}
					}

				}
			}
		} else
			SystemLog.error("Connect to distributionUrl error.");
		DirAndFile.deleteFile(localPath);
		SystemLog.info("Delete the " + localPath + " success.");
	}

	private void paramThunderTxt() {
		String filepath = SystemInit.jarCurDir + "1td.txt";
		String content = DirAndFile.loadStrFromFile(filepath);
		String[] conlist = content.split("\r\n");
		boolean isfz = false;
		ThunderData mthunderdata = new ThunderData();
		ThunderData fthunderdata = new ThunderData();

		for (int i = 0; i < conlist.length; i++) {
			if (conlist[i].contains("ÿһ��")) {
				isfz = true;
				continue;
			}
			String[] valuelist = conlist[i].split("	");
			if (valuelist.length >= 4) {
				ThunderValue thundervalue = new ThunderValue();
				thundervalue.times[0] = Double.parseDouble(valuelist[3]);

				if (!isfz) {
					thundervalue.point.add(Double.parseDouble(valuelist[0]));
					thundervalue.point.add(Double.parseDouble(valuelist[1]));
					thundervalue.point.add(Double.parseDouble(valuelist[2]));
					mthunderdata.elementValuelist.add(thundervalue);
				} else {
					thundervalue.times[1] = Double.parseDouble(valuelist[7]);
					thundervalue.point.add(Double.parseDouble(valuelist[0]));
					thundervalue.point.add(Double.parseDouble(valuelist[1]));
					thundervalue.point.add(Double.parseDouble(valuelist[2]));
					thundervalue.point.add(Double.parseDouble(valuelist[4]));
					thundervalue.point.add(Double.parseDouble(valuelist[5]));
					thundervalue.point.add(Double.parseDouble(valuelist[6]));
					fthunderdata.elementValuelist.add(thundervalue);
				}

			}
		}

		Collections.sort(mthunderdata.elementValuelist, new SortByTime());
		Collections.sort(fthunderdata.elementValuelist, new SortByTime());
		ArrayList<ArrayList<ThunderValueRel>> mainlist = new ArrayList<ArrayList<ThunderValueRel>>();
		ArrayList<ThunderValueRel> mlist = new ArrayList<ThunderValueRel>();
		ThunderData result = new ThunderData();
		for (int i = 0; i < mthunderdata.elementValuelist.size(); i++) {
			Double t1;
			Double t2;
			if (i + 1 < mthunderdata.elementValuelist.size()) {
				t1 = mthunderdata.elementValuelist.get(i).times[0];
				t2 = mthunderdata.elementValuelist.get(i + 1).times[0];

			} else {
				t1 = mthunderdata.elementValuelist.get(i).times[0];
				t2 = mthunderdata.elementValuelist.get(i).times[0];
			}
			boolean hasfz = false;
			result.elementValuelist.add(mthunderdata.elementValuelist.get(i));
			ThunderValueRel newth = new ThunderValueRel();
			newth.times = mthunderdata.elementValuelist.get(i).times[0];
			newth.point = mthunderdata.elementValuelist.get(i).point;
			newth.type = "M";
			mlist.add(newth);
			// ArrayList<ThunderValue> tmpmlist = new ArrayList<>();
			ArrayList<ThunderValueRel> flist = new ArrayList<>();
			for (int j = 0; j < fthunderdata.elementValuelist.size(); j++) {
				if (t1 != t2) {
					if (fthunderdata.elementValuelist.get(j).times[1] >= t1
							&& fthunderdata.elementValuelist.get(j).times[1] < t2) {
						hasfz = true;
						ThunderValueRel thunder1 = new ThunderValueRel();
						ThunderValueRel thunder2 = new ThunderValueRel();
						thunder1.times = fthunderdata.elementValuelist.get(j).times[0];
						thunder1.point.add(fthunderdata.elementValuelist.get(j).point.get(0));
						thunder1.point.add(fthunderdata.elementValuelist.get(j).point.get(1));
						thunder1.point.add(fthunderdata.elementValuelist.get(j).point.get(2));
						thunder1.type = "F";

						thunder2.times = fthunderdata.elementValuelist.get(j).times[1];
						thunder2.point.add(fthunderdata.elementValuelist.get(j).point.get(3));
						thunder2.point.add(fthunderdata.elementValuelist.get(j).point.get(4));
						thunder2.point.add(fthunderdata.elementValuelist.get(j).point.get(5));
						thunder2.type = "F";

						flist.add(thunder1);
						flist.add(thunder2);
						result.elementValuelist.add(fthunderdata.elementValuelist.get(j));

					}
				} else {
					if (fthunderdata.elementValuelist.get(j).times[1] >= t1) {
						hasfz = true;
						ThunderValueRel thunder1 = new ThunderValueRel();
						ThunderValueRel thunder2 = new ThunderValueRel();
						thunder1.times = fthunderdata.elementValuelist.get(j).times[0];
						thunder1.point.add(fthunderdata.elementValuelist.get(j).point.get(0));
						thunder1.point.add(fthunderdata.elementValuelist.get(j).point.get(1));
						thunder1.point.add(fthunderdata.elementValuelist.get(j).point.get(2));
						thunder1.type = "F";
						thunder2.times = fthunderdata.elementValuelist.get(j).times[1];
						thunder2.point.add(fthunderdata.elementValuelist.get(j).point.get(3));
						thunder2.point.add(fthunderdata.elementValuelist.get(j).point.get(4));
						thunder2.point.add(fthunderdata.elementValuelist.get(j).point.get(5));
						thunder2.type = "F";
						flist.add(thunder1);
						flist.add(thunder2);
						result.elementValuelist.add(fthunderdata.elementValuelist.get(j));

					}
				}

			}
			if (hasfz) {
				mainlist.add(clone(mlist));
				addFlist(mainlist, flist);
				flist.clear();
				mlist.clear();
				mlist.add(newth);
			}

		}

		DirAndFile.saveStrToFile(JSON.toJSONString(mainlist, SerializerFeature.DisableCircularReferenceDetect),
				SystemInit.jarCurDir + "thunder1.json", "UTF-8");

	}

	/**
	 * �����֧ʱ��
	 * 
	 * @param mainlist
	 * @param flist
	 */
	private void addFlist(ArrayList<ArrayList<ThunderValueRel>> mainlist, ArrayList<ThunderValueRel> flist) {
		for (int i = 0; i < flist.size(); i += 2) {
			ArrayList<ThunderValueRel> frel = new ArrayList<ThunderValueRel>();
			frel.add(flist.get(i));
			frel.add(flist.get(i + 1));
			mainlist.add(frel);

		}

	}

	private ArrayList<ThunderValueRel> clone(ArrayList<ThunderValueRel> mlist) {
		ArrayList<ThunderValueRel> result = new ArrayList<ThunderValueRel>();
		for (int i = 0; i < mlist.size(); i++) {
			ThunderValueRel tmprel = new ThunderValueRel();
			tmprel.times = mlist.get(i).times;
			tmprel.type = mlist.get(i).type;
			for (int j = 0; j < mlist.get(i).point.size(); j++) {
				tmprel.point.add(mlist.get(i).point.get(j));
			}
			result.add(tmprel);
		}
		return result;
	}

	private static void testWriteFile() throws Exception {
		String filePath = "/data/test.log";
		String str = "test";

		FileOutputStream outputStream = new FileOutputStream(filePath);

		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");

		outputStreamWriter.write(str);

		outputStreamWriter.close();

		outputStream.close();

	}

	private static void testNewWebservice() {
		// 趋势演变数据
		JSONObject jsonObject = new JSONObject();
		JSONObject funjson = new JSONObject();
		// 接口名称
		funjson.put("Name", "Thunder.GetAnyAreaThunderCountGroupByDateType");
		// 接口版本
		funjson.put("Version", "1");
		jsonObject.put("Function", funjson);
		JSONArray param = new JSONArray();
		// 接口参数 Q1703
		param.add(getNewJsonObject("String", "3d"));
		//组合站点参数参考数据调用文档
		param.add(getNewJsonObject("String", "CG"));
		param.add(getNewJsonObject("String", "MINUS,PLUS"));
		param.add(getNewJsonObject("String", "330100_6"));
		param.add(getNewJsonObject("DateTime", new TDateTime("20200127000000").toString()));
		param.add(getNewJsonObject("DateTime", new TDateTime("20200428000000").toString()));
		param.add(getNewJsonObject("String", DirAndFile.loadStrFromFile("H:\\hanzhou\\新建文本文档.txt")));
		param.add(getNewJsonObject("String", "0.5km"));
		param.add(getNewJsonObject("String", "thunder_count"));
		param.add(getNewJsonObject("String", "onehour"));
		param.add(getNewJsonObject("String", "month"));
		
//		param.add(getNewJsonObject("Int32", "60"));
	
//		param.add(getNewJsonObject("String",
//				"airtemp_current,rain_sum_tenminute,airtemp_curhour_max,airtemp_curhour_min,wind_current,rh_current,visibility_oneminute"));
//		param.add(getNewJsonObject("DateTime", new TDateTime("20190618080000").toString()));
//		param.add(getNewJsonObject("DateTime", new TDateTime().toString()));
		jsonObject.put("Params", param);
		System.out.println(jsonObject.toJSONString());

		// 接口地址
		System.out.print(sendPost("http://localhost:8080/ws21/Weather/ZDZ?calltype=5&returntype=4",
				jsonObject.toJSONString()));


	}
	
	private static void testStreamWebservice() {
		
		
		// 接口地址
		System.out.print(sendPostStr("http://localhost:8080/ws21/Weather/ZDZ?calltype=4&returntype="));


	}
	
	
	private static Object getNewJsonObject(String typeValue, Object value) {
		JSONObject json = new JSONObject();
		json.put("Type", typeValue);
		json.put("Value", value);
		return json;
	}
	
	/**
	 * @param args
	 */

	public static String sendPost(String url, String param) {

		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("Accept", "*/*");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-Length", String.valueOf(param.length()));
			conn.setRequestProperty("Content-Type", "text/json");
			conn.setRequestProperty("Charset", "UTF-8");

			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);

			conn.connect();

			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * @param args
	 */

	public static String sendPostStr(String url) {

		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("Accept", "*/*");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-Length","3000");
			conn.setRequestProperty("Content-Type","application/octet-stream");
			conn.setRequestProperty("Charset", "UTF-8");

			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);

			conn.connect();

//			out = new PrintWriter(conn.getOutputStream());
			
			
			StreamWriter sw=new StreamWriter(conn.getOutputStream());

			String funName="Warning.GetDisasterInfoByLonLat";
			String version="1";
			String typeStr="string";
			String contentParm=DirAndFile.loadStrFromFile(SystemInit.jarCurDir+"test.txt","GBK");
//			sw.isClan=true;
			int parametersCount =1;
//			sw.writeInt(funName.length());
			sw.writeStr(funName);
//			sw.writeInt(version.length());
			sw.writeStr(version);
			sw.writeInt(parametersCount);
//			sw.writeInt(typeStr.length());
			sw.writeStr(typeStr);
//			sw.writeInt(contentParm.length());
			sw.writeStr(contentParm);
			sw.flush();
//			sw.close();
			
			

			
			// 发送请求参数
//			out.print(DirAndFile.loadBytesFromFile(SystemInit.jarCurDir+"111.dat"));
			// flush输出流的缓冲
//			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * ��ȡ��������
	 * 
	 * @return
	 */
	public static HashMap<String, Integer> loadStationIndex() {
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		String filePath = "H:\\Redist_Test\\stationcodes.index";
		ArrayList<String> conlist = DirAndFile.readFileByLines(filePath, "UTF-8");
		for (int i = 0; i < conlist.size(); i++) {
			result.put(conlist.get(i).trim(), i);
		}
		return result;
	}

	private static void testGetDayList() {
		// TODO Auto-generated method stub

	}

	/**
	 * ���Զ�̬����
	 */
	private static void proxyTest() {
		StarPersonProxy sp = new StarPersonProxy(new StarPerson());

		Person p = (Person) sp.getProxy();
		System.out.println(p.getClass().getName());

		byte[] classFile = ProxyGenerator.generateProxyClass("Proxy0", p.getClass().getInterfaces());
		DirAndFile.saveBytesToFile(classFile, SystemInit.jarCurDir + "Proxy0.class");

		p.sing("lala");
		p.dance("papa");
		p.test();
		for (int i = 0; i < p.getClass().getMethods().length; i++) {
			System.out.println(p.getClass().getMethods()[i].getName());
		}

		StarPersonProxy sp1 = new StarPersonProxy(new NormalPerson());
		Person p1 = (Person) sp1.getProxy();
		p1.sing("lala");
		p1.dance("papa");
		p1.test();

	}

	private static void testParseRedisData() throws Exception {
		String filepath = "H:\\WorkSpace\\java_p_workspace\\TestEveryThing\\StationInfo_china_county";
		String filecontent = DirAndFile.loadStrFromFile(filepath);

		DirAndFile.saveStrToFile((ZipUtils.unZipStr(filecontent)), filepath + "_test", "GBK");

	}

}
