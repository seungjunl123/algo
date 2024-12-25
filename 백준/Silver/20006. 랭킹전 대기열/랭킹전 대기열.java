import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static class User implements Comparable<User> {
		int Lv;
		String id;
		User(int Lv, String id){
			this.id = id;
			this.Lv = Lv;
		}
		@Override
		public int compareTo(User o) {
		
			return this.id.compareTo(o.id);
		}
	}
	static class Room {
		int cnt;
		int firstLv;
		boolean isFull = false;
		ArrayList<User> userList;
		Room(int cnt, int firstLv){
			this.cnt= cnt;
			this.firstLv = firstLv;
		}
	}
	
	static ArrayList<Room> lobby;
	static int p, m;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		p = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		lobby = new ArrayList<>();
		
		point : for(int P = 0;P<p;P++) {
			st = new StringTokenizer(br.readLine());
			User newUser = new User(Integer.parseInt(st.nextToken()),st.nextToken());
			
			// 자리가 있으면 드가고
			for(Room room : lobby) {
				if(!room.isFull && (room.firstLv+10>=newUser.Lv &&room.firstLv-10<=newUser.Lv)) {
					room.userList.add(newUser);
					if(room.userList.size()==m) {
						room.isFull = true;
					}
					continue point;
				}
			}
			// 없으면 방을 생성해서 드가자
			Room newRoom = new Room(1, newUser.Lv);
			newRoom.userList = new ArrayList<>() ;
			newRoom.userList.add(newUser);
			if(m==1) {
				newRoom.isFull = true;
			}
			lobby.add(newRoom);
		}
		
		for(Room room : lobby) {
			if(room.isFull) {
				bw.write("Started!\n");
			} else {
				bw.write("Waiting!\n");
			}
			Collections.sort(room.userList);
			for(User user:room.userList) {
				bw.write(user.Lv+" "+user.id+"\n");
			}
		}
		
		bw.flush();
		bw.close();


	}


}