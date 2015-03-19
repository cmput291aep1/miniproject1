package p1;

public abstract class Model {
	public abstract String generateStatement();

	protected String generateInsert(String tableName, String... fields) {
		String output="INSERT INTO "+tableName+" ";
		for(String str:fields){
			output+=","+str;
		}
		output=output.replaceFirst(",","(");
		return output+")";
	}

	protected String encapsulate(String attr) {
		return "VALUES ("+attr+")";
	}

}