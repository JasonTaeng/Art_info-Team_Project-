package com.data.dto.board.inquiry;

import java.util.List;

import com.data.dao.board.inquiry.InquiryBoardDAO;


public class InquiryPaging {
	int pageTotal;
	int onePageLimit;
	int bottomPageAll; // Math.ceil
	int bottomPageLimit;
	int bottomCurrent;
	int bottomStart ;
	int endStart ;
	List<InquiryBoard> list10;
	int pstartno;
	
	public InquiryPaging() { super(); }
	public InquiryPaging(int pstartno) {
		InquiryBoardDAO dao = new InquiryBoardDAO();
		//JSTLPaging
		//#1 전체글            :  int pageTotal      ->   256
		this.pageTotal= dao.listCnt();
		//#2 한페이지당     	 :  int onePageLimit   -> 10
		this.onePageLimit = 10;
		//#3 하단페이지수     :  int bottomPageAll ->   
					  //   	 256/10 =   25.6   => 26    21/10 = 2.1 => 3       33/10 = 3.3   => 4
		this.bottomPageAll =(int)Math.ceil(this.pageTotal / (float)this.onePageLimit);
		//#4 하단페이지당     :   int bottomPageLimit   ->    10
		this.bottomPageLimit =10;
		///////////////////////////////////////////////////////////////////////////////////////////
		//#5 하단현재페이지  	 :   int bottomCurrent  -> 5  18  25
		this.bottomCurrent= (int)Math.floor(pstartno/(float)onePageLimit)+1;
		//#6 하단페이지시작   :   int bottomStart       -> 1   11  21
		this.bottomStart = (int)(Math.floor((bottomCurrent-1)/(float)bottomPageLimit))*bottomPageLimit +1;
		//#7 하단페이지끝       :   int endStart       -> 10   20  30
		this.endStart = this.bottomStart + bottomPageLimit -1;
		// 시작 21 -> 끝 30
		//    21 -> 26
		if (this.bottomPageAll < this.endStart) {this.endStart = this.bottomPageAll;}
		///////////////////////////////////////////////////////////////////////////////////////////
		//#8 10개의 리스트      :  List<JSTLItem> list10  ->  ?,10   /0,10 / 10,10
		this.list10 = dao.listpage(pstartno);
		//#9 페이징시작번호  :  int pstartno 
		this.pstartno = pstartno;
	}

	

	public InquiryPaging(int pageTotal, int onePageLimit, int bottomPageAll, int bottomPageLimit, int bottomCurrent,
			int bottomStart, int endStart, List<InquiryBoard> list10, int pstartno) {
		super();
		this.pageTotal = pageTotal;
		this.onePageLimit = onePageLimit;
		this.bottomPageAll = bottomPageAll;
		this.bottomPageLimit = bottomPageLimit;
		this.bottomCurrent = bottomCurrent;
		this.bottomStart = bottomStart;
		this.endStart = endStart;
		this.list10 = list10;
		this.pstartno = pstartno;
	}
	@Override
	public String toString() {
		return "JSTLPaging [pageTotal=" + pageTotal + ", onePageLimit=" + onePageLimit + ", bottomPageAll="
				+ bottomPageAll + ", bottomPageLimit=" + bottomPageLimit + ", bottomCurrent=" + bottomCurrent
				+ ", bottomStart=" + bottomStart + ", endStart=" + endStart + ", list10=" + list10 + ", pstartno="
				+ pstartno + "]";
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public int getOnePageLimit() {
		return onePageLimit;
	}

	public void setOnePageLimit(int onePageLimit) {
		this.onePageLimit = onePageLimit;
	}

	public int getBottomPageAll() {
		return bottomPageAll;
	}

	public void setBottomPageAll(int bottomPageAll) {
		this.bottomPageAll = bottomPageAll;
	}

	public int getBottomPageLimit() {
		return bottomPageLimit;
	}

	public void setBottomPageLimit(int bottomPageLimit) {
		this.bottomPageLimit = bottomPageLimit;
	}

	public int getBottomCurrent() {
		return bottomCurrent;
	}

	public void setBottomCurrent(int bottomCurrent) {
		this.bottomCurrent = bottomCurrent;
	}

	public int getBottomStart() {
		return bottomStart;
	}

	public void setBottomStart(int bottomStart) {
		this.bottomStart = bottomStart;
	}

	public int getEndStart() {
		return endStart;
	}

	public void setEndStart(int endStart) {
		this.endStart = endStart;
	}

	public List<InquiryBoard> getList10() {
		return list10;
	}

	public void setList10(List<InquiryBoard> list10) {
		this.list10 = list10;
	}

	public int getPstartno() {
		return pstartno;
	}

	public void setPstartno(int pstartno) {
		this.pstartno = pstartno;
	}
}
