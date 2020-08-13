package com.cx.test.entry;

import com.hw.commons.db.datatable.DataTable;
import com.hw.hwcdp.publiccode.db.oracle.OracleBase_W_ZDZ_P;

public class ProductData extends OracleBase_W_ZDZ_P{

	/**
	 * 获取咸阳全部站点信息
	 * @return
	 */
	public DataTable loadXyDtInfo(String collectionCode) {
		String sql = "select STATIONCODE,STATIONNAME,STATIONNAME_PINYIN,STATIONTYPE,LONGITUDE,LATITUDE,"
				+ "LONGITUDE_CORRECTION,LATITUDE_CORRECTION,HEIGHT,ELEMENTCOUNT,COUNTRY,PROVINCE,CITY,COUNTY,TOWN,VILLAGE,"
				+"STATIONLEVEL_XZ,STATIONLEVEL_TYPE,ISEXCLUDE,STATIONELEMENT,PROVINCECODE,CITYCODE,COUNTYCODE,'' AREA "
				+ "from b_w_zdzcollection_station where stationcollectioncode='"+collectionCode+"' order by province,city,county";

		return super.loadDataTable(sql, null);
	}
	
	/**
	 * @return
	 */
	public DataTable loadDtInfo(String collectionCode) {
		String sql = "select STATIONCODE,STATIONNAME,STATIONNAME_PINYIN,STATIONTYPE,LONGITUDE,LATITUDE,"
				+ "LONGITUDE_CORRECTION,LATITUDE_CORRECTION,HEIGHT,ELEMENTCOUNT,COUNTRY,PROVINCE,CITY,COUNTY,TOWN,VILLAGE,"
				+"STATIONLEVEL_XZ,STATIONLEVEL_TYPE,ISEXCLUDE,STATIONELEMENT "
				+ "from zdz_collection where stationcollectioncode='"+collectionCode+"' order by province,city,county";

		return super.loadDataTable(sql, null);
	}

	public Object getStationName(String stationCode) {
		String sql ="select stationname from zdz_station where stationcode='"+stationCode+"'";
		DataTable dt  = super.loadDataTable(sql, null);
		if(null!=dt && dt.rows.size()>0)
		{
			return dt.rows.get(0).getStr("stationName".toUpperCase());
		}
		return "";
	}

}
