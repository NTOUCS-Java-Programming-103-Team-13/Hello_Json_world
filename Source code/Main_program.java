/* 用「斜線、星號」、「星號、斜線」包住的區塊為多行註解 */
/** @file Main_program.java
    @brief 「Json 世界哈囉！」主程式
    @author Ｖ字龍(Vdragon)
    @copyright Ｖ字龍放棄此專案的一切權利，將其釋出到公有領域(public domain)。 */

import java.util.Iterator;
import java.util.Scanner;
import java.io.*;

import org.json.*;
import org.apache.commons.io.*;

/** Main_program 物件類別(class)

    每個 Java 來源程式碼檔案至少要有一個 public 物件類別 */
public class Main_program{
	/** public static void main() 為 Java 程式的進入點(entry point)子程式
	 *  @param args 保存命令列參數的字串陣列，注意第一個命令列參數位於 0 索引值 */
	public static void main(String[] args){
		/** ### 1. 印出軟體名稱 */
		System.out.println("Json 世界哈囉！");
		System.out.println("－－－－－－－－－－－");
		
		/** ### 2. 提示使用者輸入 youtube-dl 命令 */
		Scanner standard_input_scanner = new Scanner(System.in);
		String youtube_dl_command = null;
		System.out.print("請輸入 youtube-dl 命令：");
		youtube_dl_command = standard_input_scanner.nextLine();
		
		/** ### 3. 呼叫並執行命令 */
		Process youtube_dl_process;
		try {
			youtube_dl_process = Runtime.getRuntime().exec(youtube_dl_command);

			youtube_dl_process.waitFor();
			
			/** ### 4. 讀取並分析 youtube-dl 輸出 */
			String line = null;
			String json_data = "";
			
			InputStream youtube_dl_process_standard_output = youtube_dl_process.getInputStream();
			BufferedReader youtube_dl_output_reader = new BufferedReader (new InputStreamReader(youtube_dl_process_standard_output));
			while((line = youtube_dl_output_reader.readLine()) != null){
				json_data += line;
			}
			youtube_dl_output_reader.close();
			JSONObject test_json_object = new JSONObject(json_data);
			System.out.println("title = " + test_json_object.get("title"));
			System.out.println("view_count = " + test_json_object.get("view_count"));
			
			JSONArray test_json_array = test_json_object.getJSONArray("formats");
			for(int array_index = 0; array_index < test_json_array.length(); ++array_index){
				System.out.println("format[" + array_index + "] = " + test_json_array.getJSONObject(array_index).get("format"));
			}
			
			if(test_json_object.isNull("subtitles") == false){
				JSONObject subtitle_object = test_json_object.getJSONObject("subtitles");
				for(Iterator<String> i = subtitle_object.keys(); i.hasNext(); ){
					System.out.println(i.next());
				}
			}
		
			test_json_array = null;
			test_json_object = null;
		} catch (IOException | InterruptedException e) {
			// TODO 自動產生的 catch 區塊
			e.printStackTrace();
		}
		

	}
}
