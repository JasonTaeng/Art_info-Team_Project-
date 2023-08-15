package com.data.dto.board.free;

import java.util.List;

import com.data.dao.board.free.FreeBoardDAO;

public class FreeBoardPaging {
	int pageTotal;
	int onePageLimit;
	int bottomPageAll;
	int bottomPageLimit;
	int bottomCurrent;
	int bottomStart;
	int endStart;
	List<FreeBoard> list10;
	int pstartno;
	
	public FreeBoardPaging() { super(); }
	
	public FreeBoardPaging(String field, String query, int pstartno) {
		FreeBoardDAO dao = new FreeBoardDAO();		
		this.pageTotal= dao.listCnt();		
		this.onePageLimit = 10;		
		this.bottomPageAll =(int)Math.ceil(this.pageTotal / (float)this.onePageLimit);		
		this.bottomPageLimit =10;		
		this.bottomCurrent= (int)Math.floor(pstartno/(float)onePageLimit)+1;		
		this.bottomStart = (int)(Math.floor((bottomCurrent-1)/(float)bottomPageLimit))*bottomPageLimit +1;		
		this.endStart = this.bottomStart + bottomPageLimit -1;		
		if (this.bottomPageAll < this.endStart) {this.endStart = this.bottomPageAll;}		
		this.list10 = dao.listpage(field, query, pstartno);		
		this.pstartno = pstartno;
	}	

	public FreeBoardPaging(int pageTotal, int onePageLimit, int bottomPageAll, int bottomPageLimit, int bottomCurrent,
			int bottomStart, int endStart, List<FreeBoard> list10, int pstartno) {
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

	public List<FreeBoard> getList10() {
		return list10;
	}

	public void setList10(List<FreeBoard> list10) {
		this.list10 = list10;
	}

	public int getPstartno() {
		return pstartno;
	}

	public void setPstartno(int pstartno) {
		this.pstartno = pstartno;
	}
}
