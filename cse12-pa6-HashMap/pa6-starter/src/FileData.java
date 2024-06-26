public class FileData {

    public String name;
    public String dir;
    public String lastModifiedDate;

    // TODO
    public FileData(String name, String directory, String modifiedDate) {
    	this.name = name;
    	this.dir = directory;
    	this.lastModifiedDate = modifiedDate;
    }

    // TODO
    public String toString() {
    	return "{Name: " + this.name + ", Directory: " + this.dir + ", Modified Date: " + this.lastModifiedDate +"}";
    }
}
