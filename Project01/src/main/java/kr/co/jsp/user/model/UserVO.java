package kr.co.jsp.user.model;

public class UserVO {
private String uId;
private  String uPw;
private  String name;
private  String email;
private  String phoneNum;
private  String address;
private  String detailAddress;

public UserVO () {}

public UserVO(String id, String pw, String name, String email,String phoneNum, String address, String detailAddress) {
//	super();
	this.uId = id;
	this.uPw = pw;
	this.name = name;
	this.email = email;
	this.phoneNum= phoneNum;
	this.address = address;
	this.detailAddress = detailAddress;
}
public  String getuId() {
	return uId;
}
public  String getDetailAddress() {
	return detailAddress;
}
public  void setDetailAddress(String detailAddress) {
	this.detailAddress = detailAddress;
}
public  void setuId(String uId) {
	this.uId = uId;
}
public  String getuPw() {
	return uPw;
}
public  void setuPw(String uPw) {
	this.uPw = uPw;
}
public  String getName() {
	return name;
}
public  void setName(String name) {
	this.name = name;
}
public  String getEmail() {
	return email;
}
public  void setEmail(String email) {
	this.email = email;
}
public  String getPhoneNum() {
	return phoneNum;
}
public  void setPhoneNum(String phoneNum) {
	this.phoneNum = phoneNum;
}
public  String getAddress() {
	return address;
}
public  void setAddress(String address) {
	this.address = address;
}

@Override
public String toString() {
	return "UserVO [uId=" + uId + ", uPw=" + uPw + ", name=" + name + ", email=" + email + ", phoneNum=" + phoneNum
			+ ", address=" + address + ", detailAddress=" + detailAddress + "]";
}



}
