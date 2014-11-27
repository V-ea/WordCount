package ea.wordcount;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TxTWordCount {
	public static void QuickSort1(int[] index, String[] words, Object[] arr,
			int begin, int end) {
		int i = begin;
		int j = end;

		if (i >= j) {
			return;
		}
		int temp = index[i];
		int tempValue = (Integer) arr[temp];
		while (i < j) {
			while (i < j && (Integer) arr[index[j]] >= tempValue) {
				j--;
			}
			index[i] = index[j];
			// System.out.print("\tswap "+index[j]);
			while (i < j && (Integer) arr[index[i]] <= tempValue) {
				i++;
			}
			index[j] = index[i];
			// System.out.print("\tswap "+index[i]);
		}
		// for(int jjj=0;jjj<10;jjj++)
		// System.out.print(index[jjj]+"\t");
		// System.out.print(0);
		index[i] = temp;
		// System.out.println(i);
		QuickSort1(index, words, arr, begin, i - 1);
		QuickSort1(index, words, arr, i + 1, end);
	}

	public static void Start(String from,String to) throws IOException {
		FileWriter fw = new FileWriter(to);
		// read
		fw.write("read file,loading......\r\n");
		BufferedReader br = new BufferedReader(new FileReader(from));
		String dat = br.readLine();// 涓�璇诲叆涓�锛岀洿鍒拌鍏ull涓烘枃浠剁粨鏉�
		String data = "";
		data += dat;
		while (dat != null) {
			// System.out.println(data);
			dat = br.readLine(); // 鎺ョ潃璇讳笅涓�
			data += dat;
		}
		data = data.replace('(', ' ').replace(')', ' ')
				.replace('\'', ' ').replace('_', ' ').replace('[', ' ')
				.replace(':', ' ')
				.replace(']', ' ')
				.toLowerCase();
		br.close();
		fw.write("statistic:\r\n");
		fw.write("||chars's count:" + data.length()+"\r\n");
		// split
		String[] words = data.split(" ");
		Map<String, Integer> countMap = new HashMap<String, Integer>();
		if (words == null) {
			return;
		}
		for (int i = 0; i < words.length; i++) {
			String str = words[i].trim();
			if (str.equals("")) {
				continue;
			}
			// if(countMap.size()>100)break;
			if (countMap.get(str) == null) {
				countMap.put(str, 1);
			} else {
				Integer tmp = (Integer) countMap.get(str) + 1;
				countMap.put(str, tmp);
			}
		}
		fw.write("||words's count:" + countMap.size()+"\r\n");
		// sort
		Set<String> keys = countMap.keySet();
		Collection<Integer> values = countMap.values();
		String[] keysStr = new String[values.size()];
		Object[] valuesA = new Object[values.size()];
		int[] keysStrSorted = new int[values.size()];
		valuesA = values.toArray();
		keys.toArray(keysStr);
		if (valuesA == null) {
			return;
		}
		for (int j = 0; j < valuesA.length; j++)
			keysStrSorted[j] = j;
		int lllll = 3000;
		int count = values.size() % lllll == 0 ? values.size() / lllll
				: (values.size() / lllll + 1);
		int indexMerge[] = new int[count];
		int indexEnd[] = new int[count];
		for (int jkl = 0; jkl < count; jkl++) {
			indexMerge[jkl] = jkl * lllll;
			indexEnd[jkl] = ((jkl + 1) * lllll - 1) >= values.size() ? values
					.size() - 1 : ((jkl + 1) * lllll - 1);
		}
		for (int qs = 0; qs < count; qs++) {
			QuickSort1(keysStrSorted, keysStr, valuesA, indexMerge[qs],
					indexEnd[qs]);
			fw.write("from :"+indexMerge[qs]+"|to:"+indexEnd[qs]+"\r\n");
		}
		// merge
		int mergeSorted[] =new int[valuesA.length];
		int index =0;
		boolean end = true;
		//check all end
		for(int i=0;i<count;i++)
		{
			if(indexMerge[i]<=indexEnd[i])
			{
				end =false;
			}
		}
		while(end == false)
		{
			
			//check all end
			end =true;
			int smallest = -1;
			for(int i=0;i<count;i++)
			{
				if(indexMerge[i]>indexEnd[i])
				{
					continue;
				}
				fw.write("\t"+i);
				if(smallest ==-1)
				{
					smallest =i;
				}else
				if((Integer)valuesA[keysStrSorted[indexMerge[i]]]<(Integer)valuesA[keysStrSorted[indexMerge[smallest]]])
				{
					smallest =i;
				}
			}
			fw.write("result:"+smallest+"|:"+(Integer)valuesA[keysStrSorted[indexMerge[smallest]]]+"\r\n");
			mergeSorted[index]=keysStrSorted[indexMerge[smallest]];
			index ++;
			indexMerge[smallest]++;
			for(int i=0;i<count;i++)
			{
				if(indexMerge[i]<=indexEnd[i])
				{
					end =false;
				}
			}
		}
		// save result
		fw.write("-------------------result----\r\n");
		for (int l = values.size() - 1; l >= 0; l--) {
			fw.write(keysStr[mergeSorted[l]] + ":"
					+ countMap.get(keysStr[mergeSorted[l]])+"\r\n");
		}
		
		fw.close();
	}
}
