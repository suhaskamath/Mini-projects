#include<iostream>
#include<conio.h>
#include<bits/stdc++.h>
using namespace std;
class print
{
public:
   	void screenheader();
   	void options();
   	void intro();
   	friend class travel;	
};
class travel
{
protected: int no_rooms,cost_hot,pass_no,noofdays,age[8],fare[5][5],distance[6][6],transport_no[6][6],i,j,cost;
	string name[8],gender[8],dob[8],cities[6],user_name[2],password[2],hotel_name;
	print p2;
public:
		void travel_mumbai();
		void travel_kolkata();
		void travel_delhi();
		void travel_amritsar();
		void travel_bangalore();
		void get_info();
		void create();
		travel friend encrypt(travel t1);
		travel friend decrypt(travel t1);
		void cal_tax(int n);
		void cal_tax(int n2,int n3);
     travel()
	{
		
			cities[0].append("Delhi");
	        cities[1].append("Bangalore");
	        cities[2].append("Kolkata");
	        cities[3].append("Amritsar");
	        cities[4].append("Mumbai");
	        
	}
	    
};
class air_travel:public virtual travel
{
	public:		void travel2()
		{
			int fare1[5][5]={{0,3000,2500,2350,2600},{3000,0,3500,3000,2500},{2500,3000,0,2200,3000},{2350,3000,2300,0,3300},{3000,2500,3000,3300,0}};
			int distance1[5][5]={{0,1750,1000,800,1000},{1750,0,2100,1800,1200},{700,2100,0,1100,1895},{800,1900,1100,0,1990},{1000,1200,1100,1990,0}};
			for(int i=0;i<5;i++)
			{
				for(int j=0;j<5;j++)
				{
					fare[i][j]=fare1[i][j];
					distance[i][j]=distance1[i][j];
				}
			}
			
		} 
		void book_airticket();
				
};
class bus_travel:public virtual travel
{
		public:		void travel1()
		{
					int fare1[5][5]={{0,3000,2500,2350,2600},{3000,0,3500,3000,2500},{2500,3000,0,2200,3000},{2350,3000,2300,0,3300},{3000,2500,3000,3300,0}};
			int distance1[5][5]={{0,1750,1000,800,1000},{1750,0,2100,1800,1200},{700,2100,0,1100,1895},{800,1900,1100,0,1990},{1000,1200,1100,1990,0}};
			for(int i=0;i<5;i++)
			{
				for(int j=0;j<5;j++)
				{
					fare[i][j]=fare1[i][j];
					distance[i][j]=distance1[i][j];
				}
			}
		}
		void book_busticket();
};
class details:public air_travel,public bus_travel
{ public:
void display();
int operator !=(string s1)
 {
  if(s1.compare(password[0])==0)
  {
   return 1;
 }
	return 0;
 }
  int operator ==(string s1)
 {
  if(s1.compare(user_name[0])==0)
	{
	 return 1;
	}
	return 0;
 }
};
class admin
{
 private:string password;
 public:
 admin()
 {
  password.append("admin-456");
 }
 int operator ==(string s1)
 {
  if(s1.compare(password)==0)
	{
	 return 1;
	}
	return 0;
 }
};

/***************************************************************************************************************************************************** */
void travel::get_info()
   {
   	cout<<"Enter the Starting point of your Liking\n";
   	p2.options();
	cin>>i;
	i=i-1;
	cout<<"Enter the destination of your Liking\n";
	cin>>j;
	j=j-1;
   cout<<"Enter the Number of passengers";
   cin>>pass_no;
   for(int i=0;i<pass_no;i++)
   {
   cout<<"Enter the Passenger Detials"<<i+1<<endl;
			cout<<"Enter the Name  of Traveller"<<endl;
			cin>>name[i];
			cout<<"Enter the age of the Traveller"<<endl;
			cin>>age[i];
			cout<<"Enter the Date of Birth "<<endl;
			cin>>dob[i];
			cout<<"Enter the gender"<<endl;
			cin>>gender[i];
    }
   }
void travel::create()
{
	p2.screenheader();
	cout<<"Enter the Name of the User "<<endl;
	cin>>user_name[0];
	cout<<"Enter the password"<<endl;
	cin>>password[0];
	
}
void travel::travel_mumbai()
{
	int ch;
	cout<<"The Hotels available in Mumbai are";
	cout<<"1-The Orchid------->4000 per day for a room of three\n2-Novotel Mumbai Juhu Beach------->2500 per day for a room of three\n3-The Leela Mumbai------->6000 per day for a room of three\n4-The Fern Goregaon------->3000 per day for a room of three\n";
    cin>>ch;
    switch(ch)
    {
    	case 1:hotel_name.append("The Orchid");
    	cout<<"Enter the no of days of stay"<<endl;
    	cin>>noofdays;
    	no_rooms=pass_no/3;
    	if(pass_no%3!=0)
    	no_rooms++;
    	cost_hot=4000*noofdays*(no_rooms);
    	break;
    	case 2:hotel_name.append("Novotel Mumbai Juhu Beach");
    	cout<<"Enter the no of days of stay"<<endl;
    	cin>>noofdays;
    	no_rooms=pass_no/3;
    	if(pass_no%3!=0)
    	no_rooms++;
    	cost_hot=2500*noofdays*(no_rooms);
    	break;
    	case 3:hotel_name.append("The Leela Mumbai");
    	cout<<"Enter the no of days of stay";
    	cin>>noofdays;
    	no_rooms=pass_no/3;
    	if(pass_no%3!=0)
    	no_rooms++;
    	cost_hot=6000*noofdays*(no_rooms);
    	break;
    	case 4:hotel_name.append("The Fern Goregaon");
    	cout<<"Enter the no of days of stay"<<endl;
    	cin>>noofdays;
    	no_rooms=pass_no/3;
    	if(pass_no%3!=0)
    	no_rooms++;
    	cost_hot=3000*noofdays*(no_rooms);
    	break;	     
	}
	cout<<"Total cost Without Tax is"<<cost_hot;
	cal_tax(cost_hot);
	cout<<"Total cost With Cost is"<<cost_hot;
}
void travel::travel_amritsar()
{
		int ch;
	cout<<"The Hotels available in Mumbai are";
	cout<<"1-The Orchid------->4000 per day for a room of three\n2-Novotel Mumbai Juhu Beach------->2500 per day for a room of three\n3-The Leela Mumbai------->6000 per day for a room of three\n4-The Fern Goregaon------->3000 per day for a room of three\n";
    cin>>ch;
    switch(ch)
    {
    	case 1:hotel_name.append("The Orchid");
    	cout<<"Enter the no of days of stay"<<endl;
    	cin>>noofdays;
    	no_rooms=pass_no/3;
    	if(pass_no%3!=0)
    	no_rooms++;
    	cost_hot=4000*noofdays*(no_rooms);
    	break;
    	case 2:hotel_name.append("Novotel Mumbai Juhu Beach");
    	cout<<"Enter the no of days of stay"<<endl;
    	cin>>noofdays;
    	no_rooms=pass_no/3;
    	if(pass_no%3!=0)
    	no_rooms++;
    	cost_hot=2500*noofdays*(no_rooms);
    	break;
    	case 3:hotel_name.append("The Leela Mumbai");
    	cout<<"Enter the no of days of stay";
    	cin>>noofdays;
    	no_rooms=pass_no/3;
    	if(pass_no%3!=0)
    	no_rooms++;
    	cost_hot=6000*noofdays*(no_rooms);
    	break;
    	case 4:hotel_name.append("The Fern Goregaon");
    	cout<<"Enter the no of days of stay"<<endl;
    	cin>>noofdays;
    	no_rooms=pass_no/3;
    	if(pass_no%3!=0)
    	no_rooms++;
    	cost_hot=3000*noofdays*(no_rooms);
    	break;	     
	}
	cout<<"Total cost Without Tax is"<<cost_hot;
	cal_tax(cost_hot);
	cout<<"Total cost With Cost is"<<cost_hot;	
}
void travel::travel_bangalore()
{
		int ch;
	cout<<"The Hotels available in Mumbai are";
	cout<<"1-The Orchid------->4000 per day for a room of three\n2-Novotel Mumbai Juhu Beach------->2500 per day for a room of three\n3-The Leela Mumbai------->6000 per day for a room of three\n4-The Fern Goregaon------->3000 per day for a room of three\n";
    cin>>ch;
    switch(ch)
    {
    	case 1:hotel_name.append("The Orchid");
    	cout<<"Enter the no of days of stay"<<endl;
    	cin>>noofdays;
    	no_rooms=pass_no/3;
    	if(pass_no%3!=0)
    	no_rooms++;
    	cost_hot=4000*noofdays*(no_rooms);
    	break;
    	case 2:hotel_name.append("Novotel Mumbai Juhu Beach");
    	cout<<"Enter the no of days of stay"<<endl;
    	cin>>noofdays;
    	no_rooms=pass_no/3;
    	if(pass_no%3!=0)
    	no_rooms++;
    	cost_hot=2500*noofdays*(no_rooms);
    	break;
    	case 3:hotel_name.append("The Leela Mumbai");
    	cout<<"Enter the no of days of stay";
    	cin>>noofdays;
    	no_rooms=pass_no/3;
    	if(pass_no%3!=0)
    	no_rooms++;
    	cost_hot=6000*noofdays*(no_rooms);
    	break;
    	case 4:hotel_name.append("The Fern Goregaon");
    	cout<<"Enter the no of days of stay"<<endl;
    	cin>>noofdays;
    	no_rooms=pass_no/3;
    	if(pass_no%3!=0)
    	no_rooms++;
    	cost_hot=3000*noofdays*(no_rooms);
    	break;	     
	}
	cout<<"Total cost Without Tax is"<<cost_hot;
	cal_tax(cost_hot);
	cout<<"Total cost With Cost is"<<cost_hot;
}
void travel::travel_delhi()
{
		int ch;
	cout<<"The Hotels available in Mumbai are";
	cout<<"1-The Orchid------->4000 per day for a room of three\n2-Novotel Mumbai Juhu Beach------->2500 per day for a room of three\n3-The Leela Mumbai------->6000 per day for a room of three\n4-The Fern Goregaon------->3000 per day for a room of three\n";
    cin>>ch;
    switch(ch)
    {
    	case 1:hotel_name.append("The Orchid");
    	cout<<"Enter the no of days of stay"<<endl;
    	cin>>noofdays;
    	no_rooms=pass_no/3;
    	if(pass_no%3!=0)
    	no_rooms++;
    	cost_hot=4000*noofdays*(no_rooms);
    	break;
    	case 2:hotel_name.append("Novotel Mumbai Juhu Beach");
    	cout<<"Enter the no of days of stay"<<endl;
    	cin>>noofdays;
    	no_rooms=pass_no/3;
    	if(pass_no%3!=0)
    	no_rooms++;
    	cost_hot=2500*noofdays*(no_rooms);
    	break;
    	case 3:hotel_name.append("The Leela Mumbai");
    	cout<<"Enter the no of days of stay";
    	cin>>noofdays;
    	no_rooms=pass_no/3;
    	if(pass_no%3!=0)
    	no_rooms++;
    	cost_hot=6000*noofdays*(no_rooms);
    	break;
    	case 4:hotel_name.append("The Fern Goregaon");
    	cout<<"Enter the no of days of stay"<<endl;
    	cin>>noofdays;
    	no_rooms=pass_no/3;
    	if(pass_no%3!=0)
    	no_rooms++;
    	cost_hot=3000*noofdays*(no_rooms);
    	break;	     
	}
	cout<<"Total cost Without Tax is"<<cost_hot;
	cal_tax(cost_hot);
	cout<<"Total cost With Cost is"<<cost_hot;
}
void travel::travel_kolkata()
{
		int ch;
	cout<<"The Hotels available in Mumbai are";
	cout<<"1-The Orchid------->4000 per day for a room of three\n2-Novotel Mumbai Juhu Beach------->2500 per day for a room of three\n3-The Leela Mumbai------->6000 per day for a room of three\n4-The Fern Goregaon------->3000 per day for a room of three\n";
    cin>>ch;
    switch(ch)
    {
    	case 1:hotel_name.append("The Orchid");
    	cout<<"Enter the no of days of stay"<<endl;
    	cin>>noofdays;
    	no_rooms=pass_no/3;
    	if(pass_no%3!=0)
    	no_rooms++;
    	cost_hot=4000*noofdays*(no_rooms);
    	break;
    	case 2:hotel_name.append("Novotel Mumbai Juhu Beach");
    	cout<<"Enter the no of days of stay"<<endl;
    	cin>>noofdays;
    	no_rooms=pass_no/3;
    	if(pass_no%3!=0)
    	no_rooms++;
    	cost_hot=2500*noofdays*(no_rooms);
    	break;
    	case 3:hotel_name.append("The Leela Mumbai");
    	cout<<"Enter the no of days of stay";
    	cin>>noofdays;
    	no_rooms=pass_no/3;
    	if(pass_no%3!=0)
    	no_rooms++;
    	cost_hot=6000*noofdays*(no_rooms);
    	break;
    	case 4:hotel_name.append("The Fern Goregaon");
    	cout<<"Enter the no of days of stay"<<endl;
    	cin>>noofdays;
    	no_rooms=pass_no/3;
    	if(pass_no%3!=0)
    	no_rooms++;
    	cost_hot=3000*noofdays*(no_rooms);
    	break;	     
	}
	cout<<"Total cost Without Tax is"<<cost_hot;
	cal_tax(cost_hot);
	cout<<"Total cost With Cost is"<<cost_hot;

}
void travel::cal_tax(int n)
{
	if(cost_hot>1000&&cost_hot<5000)
	cost_hot=n*1.05;
	else
	{
		cost_hot=n*1.08; 
	}
}
void travel::cal_tax(int n2,int n3)
{
	if(n2==1)
	{
		cost=n3*1.05;
	}
	else
	{
		cost=n3*1.08;
	}
}
/* *********************************************************************************************************************************************************************************************** */
void print::intro()
{
   	    cout<<"============================================================================================================================================================" <<endl;
		cout<<"                                    PROJECT OF ENCRYPTION ON TRAVEL MANAGEMENT" <<endl;
		cout<<"============================================================================================================================================================" <<endl;
		cout<<"" <<endl;
		cout<<"" <<endl;
		cout<<"                                                               ================================" <<endl;
		cout<<"                                                               Horizon Ticket Booking and Tours" <<endl;
		cout<<"                                                               ================================" <<endl;
		cout<<"" <<endl;
		cout<<"............................................................................................................................................................" <<endl;
		cout<<"                       You are welcome to Horizon Ticket Booking and Tours......Thanks for choosing Horizon Ticket Booking and Tours!" <<endl;
		cout<<"............................................................................................................................................................" <<endl;
		cout<<"" <<endl;
		cout<<"" <<endl;
		cout<<"We have our branches in all the major cities of India....:" <<endl;
		cout<<"" <<endl;
		cout<<"-----------------------------------" <<endl;
		cout<<"| 1) Bengalooru -- Karnataka     |" <<endl;
		cout<<"| 2) Kolkata -- West Bengal      |" <<endl;
		cout<<"| 3) Mumbai -- Maharashtra       |" <<endl;
		cout<<"| 4) New Delhi -- Delhi          |" <<endl;
		cout<<"| 5) Darjelling -- West Bengal   |" <<endl;
		cout<<"-----------------------------------" <<endl;
		cout<<"" <<endl;
}
void print::options()
{
	cout<<"Type '1' for Delhi" <<endl;
	cout<<"Type '2' for Bangalore" <<endl;
	cout<<"Type '3' for Kolkata" <<endl;
	cout<<"Type '4' for Amritssar" <<endl;
	cout<<"Type '5' for Mumbai" <<endl;
	cout<<"Your Choice: ";
}
void print::screenheader()
{
   printf("\n                                                                                                                          ");
   printf("\n                                                                                              @@@@@@@@@@@@@@@@@@@@@@@     ");
   printf("\n                                                                                              @                     @     ");
   printf("\n                                                                                              @    HORIZON TRAVELS  @     ");
   printf("\n                                                                                              @                     @     ");
   printf("\n                                                                                              @     BANGALURU       @     ");
   printf("\n                                                                                              @@@@@@@@@@@@@@@@@@@@@@@     ");	
 		
}
/************************************************************************************************************************************************************************************************ */
void air_travel::book_airticket()
{
 cout<<"The filghts available are 1-Air_India\n2-Indigo\n3-Airasia\n4-Spicejet\n5-Vistara" ;
}
 /* *********************************************************************************************************************************************************************************************** */
travel encrypt(travel t1)
{
 	
}
travel decrypt(travel t1)
{
 	
}
/********************************************************************************************************************************************************************************************************/
void details::display()
{
	
}
/******************************************************************************************************************************************************************************************************** */
int main()
{
	ifstream count,d_read;
	details d[50];
	admin a1;
	print p1;
	string admin_pass,user_name,user_password;
	d_read.open("data_base.txt",ios::app|ios::binary);
	count.open("user.txt",ios::app|ios::binary);
	int n,ch,ch1,i;
	p1.intro();
	getch();
	system("cls");
	do
	{
	cout<<"Enter the choice \n1-Admin\n2-User\n3-Exit\n";
	cin>>ch;
	if(ch==1)
	{
		cout<<"Enter the Password";
		cin>>admin_pass;
		if(a1==admin_pass)
		{
		cout<<"1-Display All The Users\n2-Display A Particlar user\n3-Create new User";
		cin>>ch1;
		system("cls");
		switch(ch)
		    {
			  case 1:for(int i=0;i<n;i++)
			  {
			  	cout<<"User "<<i+1;
			  	//d[i].display();
			  
			  }
			  break;
			  case 2:cout<<"Enter the user name "<<endl;
			  cin>>user_name;
			  for(int i=0;i<n;i++)
			  	{
			  		/*if(d[i]==user_name)
			  		{
			  			d[i].display();
			  			
					  }*/
				  }
			  break;
			  case 3:cout<<"Enter the Details of New User"<<endl;
			  d[n].create();
			  break;
			  default:cout<<"Invaild Option";
			  break;
			}	
		}
		else
		{
			cout<<"Enter the username ";
			cin>>user_name;
			for(i=0;i<n;i++)
			{
				if(d[i]==user_name)
				{
				break;	
				}
			}
			cout<<"Enter the Password";
			cin>>user_password;
			if(d[i]!=user_password)
			{
				cout<<"Wrong Password";
				break;
			}
			else
			{
			 int ch2;
			 cout<<"1-Book Ticket\n2-Veiw booked details\n";
			 cin>>ch2;
			 if(ch2==1)
			 {
				d[i].get_info();
				cout<<"Enter the mode of travel 1-Flight\n2-Bus\n";
			 } 
			 else if(ch2==2)
			 {
				d[i].display();
			 }
		}
		}
	}
}while(ch==1||ch==2);	
}
