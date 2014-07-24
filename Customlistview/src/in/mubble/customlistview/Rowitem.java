package in.mubble.customlistview;

public class Rowitem {
	
  private String name;
  private int imageid;
  
  public Rowitem(String name, int imageid) {
    this.imageid = imageid;
    this.name = name; 
  }
  
  public int getimageId(){
    return imageid;	  
  }
  
  public String getname() {
    return name;  
  }
  
  public void setimageId(int imageid){
	this.imageid=imageid;  
  }
  
  public void setname(String name){
	this.name=name;  
  }

  public void set(int imageid,String name) {
	// TODO Auto-generated method stub
	this.name=name;
	this.imageid=imageid;
	
  } 

  @Override
  public String toString(){
	return name;
  }
}
