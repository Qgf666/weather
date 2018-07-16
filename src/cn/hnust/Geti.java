package cn.hnust;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import cn.hnust.DBConnection;

public class Geti extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/plain;charset=utf-8");
		
		try {
			////////////
			// do something
			////////////
			DBConnection db = new DBConnection();
			ResultSet rs = db.executeQuery("select * from question;");
			
			int id = 0;
			String question = "";
			String opt_one = "";
			String opt_two = "";
			String opt_three = "";
			String opt_four = "";
			String anwser = "";
			
			int count = 0;
			ArrayList<Question> list = new ArrayList<Question>();
			while(rs.next()){
				count++;
				id = rs.getInt(1);
				question = rs.getString(2);
				opt_one = rs.getString(3);
				opt_two = rs.getString(4);
				opt_three = rs.getString(5);
				opt_four = rs.getString(6);
				anwser = rs.getString(7);
				Question q = new Question(id, question, opt_one, opt_two, opt_three,opt_four,anwser);
				list.add(q);
			}
			db.close();
			
			Random r = new Random();
			int j=0;
			int[] ques= {-1,-1,-1,-1};
			while(j<4) {
				int randnumber = r.nextInt(list.size());
				int k = 0;
				while(k < 4){
					if(ques[k] == randnumber)
						break;
						k++;
				}
				if(k == 4){ 
					ques[j] = randnumber;
					j++;
				}
			}
//			int h = 0,p = 1;
//			for(int k = 0; k < 4; k++){
//				Question s = list.get(ques[h]);
//				System.out.print(p + " . ");
//				System.out.println(s.getQuestion());
//				h++;
//				p++;
//			}	
			
			int h = 0;
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();
			
			JSONObject obj1 = new JSONObject();
			Question q1 = list.get(ques[h]);
			obj1.put("id", q1.id);
			obj1.put("question", q1.question);
			obj1.put("opt_one", q1.opt_one);
			obj1.put("opt_two", q1.opt_two);
			obj1.put("opt_three", q1.opt_three);
			obj1.put("opt_four", q1.opt_four);
			obj1.put("anwser", q1.anwser);
			obj.put("question1",obj1);
			h++;
			
			JSONObject obj2 = new JSONObject();
			Question q2 = list.get(ques[h]);
			obj2.put("id", q2.id);
			obj2.put("question", q2.question);
			obj2.put("opt_one", q2.opt_one);
			obj2.put("opt_two", q2.opt_two);
			obj2.put("opt_three", q2.opt_three);
			obj2.put("opt_four", q2.opt_four);
			obj2.put("anwser", q2.anwser);
			obj.put("question2",obj2);
			h++;
			
			JSONObject obj3 = new JSONObject();
			Question q3 = list.get(ques[h]);
			obj3.put("id", q3.id);
			obj3.put("question", q3.question);
			obj3.put("opt_one", q3.opt_one);
			obj3.put("opt_two", q3.opt_two);
			obj3.put("opt_three", q3.opt_three);
			obj3.put("opt_four", q3.opt_four);
			obj3.put("anwser", q3.anwser);
			obj.put("question3",obj3);
			h++;
			
			JSONObject obj4 = new JSONObject();
			Question q4 = list.get(ques[h]);
			obj4.put("id", q4.id);
			obj4.put("question", q4.question);
			obj4.put("opt_one", q4.opt_one);
			obj4.put("opt_two", q4.opt_two);
			obj4.put("opt_three", q4.opt_three);
			obj4.put("opt_four", q4.opt_four);
			obj4.put("anwser", q4.anwser);
			obj.put("question4",obj4);
			h++;
			
			System.out.println(obj.toString());
			out.print(obj.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Geti();
	}

}
