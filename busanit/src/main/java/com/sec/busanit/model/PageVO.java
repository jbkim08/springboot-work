package com.sec.busanit.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PageVO {
	private int totPage;
	private int blockSize;
	private int startPage;
	private int endPage;
	private int currentPage;
	private int pageSize;
	
	public PageVO(int count, int currentPage, int pageSize) {
		totPage = (int)Math.ceil((double)count/pageSize);
		blockSize=3;
		startPage=((currentPage-1)/blockSize)*blockSize+1;
		endPage = startPage+blockSize-1;
		
		if(endPage>totPage)
			endPage=totPage;
		
		setBlockSize(blockSize);
		setCurrentPage(currentPage);
		setEndPage(endPage);
		setStartPage(startPage);
		setTotPage(totPage);
		setPageSize(pageSize);
	}
}
