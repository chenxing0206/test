package com.cx.test.tool;

import java.util.ArrayList;

public class DistributionData {
	private ArrayList<FtpDistribution> ftp;
	private ArrayList<LocalDistribution> localpath;
	
	public ArrayList<FtpDistribution> getFtp() {
		return ftp;
	}
	public void setFtp(ArrayList<FtpDistribution> ftp) {
		this.ftp = ftp;
	}
	public ArrayList<LocalDistribution> getLocalpath() {
		return localpath;
	}
	public void setLocalpath(ArrayList<LocalDistribution> localpath) {
		this.localpath = localpath;
	}

	
	
	
}
