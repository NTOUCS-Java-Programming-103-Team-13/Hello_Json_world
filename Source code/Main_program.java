/* 用「斜線、星號」、「星號、斜線」包住的區塊為多行註解 */
/** @file Main_program.java
    @brief 「Json 世界哈囉！」主程式
    @author Ｖ字龍(Vdragon)
    @copyright Ｖ字龍放棄此專案的一切權利，將其釋出到公有領域(public domain)。 */

import org.json.*;
import java.util.Scanner;

/** Main_program 物件類別(class)

    每個 Java 來源程式碼檔案至少要有一個 public 物件類別 */
public class Main_program{
	/** public static void main() 為 Java 程式的進入點(entry point)子程式
	 *  @param args 保存命令列參數的字串陣列，注意第一個命令列參數位於 0 索引值 */
	public static void main(String[] args){
		String json_data = new String("");
		Scanner standard_input_scanner = new Scanner(System.in);
		
		System.out.println("Json 世界哈囉！");
		System.out.println("－－－－－－－－－－－");
		System.out.println("請輸入 Json 格式資料，輸入完請按 end of file(EOF) 組合按鍵：");
		
		while(standard_input_scanner.hasNext()){
			json_data += standard_input_scanner.next();
		}
		standard_input_scanner.close();
		
		JSONObject test_json_object = new JSONObject(json_data);
		System.out.println("title = " + test_json_object.get("title"));
		System.out.println("view_count = " + test_json_object.get("view_count"));
		
		JSONArray test_json_array = test_json_object.getJSONArray("formats");
		for(int array_index = 0; array_index < test_json_array.length(); ++array_index){
			System.out.println("format[" + array_index + "] = " + test_json_array.getJSONObject(array_index).get("format"));
		}
		
		test_json_array = null;
		test_json_object = null;
	}
}
