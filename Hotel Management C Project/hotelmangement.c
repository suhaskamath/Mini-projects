#include<stdio.h>
#include<conio.h>
#include<stdlib.h>
typedef struct node
{
	int val;
	char name[30];
	struct node *next;
}p;
p *head=NULL;
int z,c1=0,c2=0,c3=0,c4=0,c5=0;
void room_avail()
{
FILE *p1;
p1=fopen("room.txt","a");
fscanf(p1,"%d",&c1);
fscanf(p1,"%d",&c2);
fscanf(p1,"%d",&c3);
fscanf(p1,"%d",&c4);
fscanf(p1,"%d",&c5);
fclose(p1);
}
void room_write()
{
	FILE *p1;
	p1=fopen("room.txt","w");
	fprintf(p1,"%d\t",c1);
	fprintf(p1,"%d\t",c2);
	fprintf(p1,"%d\t",c3);
	fprintf(p1,"%d\t",c4);
	fprintf(p1,"%d\t",c5);
	fclose(p1);
}
void insert()
{		p *t,*nn;
	FILE *p1;
	char id[50];
    int val,g;
    printf("\nEnter the 1-employee or 2-customer");
    scanf("%d",&g);
    if(g==1)
	{
	p1=fopen("emp.txt","r");
    }
	else
	{
	p1=fopen("customer.txt","r");
    }
    head=NULL;
	while(fscanf(p1,"%d",&val),fscanf(p1,"%s",id)==1)
	{	
	if(head==NULL)
    {
    	head=(p*)malloc(sizeof(p));
    	head->val=val;
    	strcpy(head->name,id);
    	head->next=NULL;
    	t=head;
		}
		else
		{
		nn=(p*)malloc(sizeof(p));
		nn->val=val;
    	nn->next=NULL;
		strcpy(nn->name,id);
		t->next=nn;
		t=nn;
			}	
}
	fclose(p1);
}
void write_data()
{
	FILE *p1;
	p *start;
	int ch;
	printf("\nEnter the 1-employee or 2-customer");
    scanf("%d",&ch);
    if(ch==1)
    {
	p1=fopen("emp.txt","w");
    }
    else
    {
    p1=fopen("customer.txt","w");	
	}
	start=head;
	if(head!=NULL)
	while(start!=NULL)
	{
		fprintf(p1,"%d\t",start->val);
		fprintf(p1,"%s\n",start->name);
		start=start->next;
	}
	fclose(p1);
}
void search()
{
p *t;
t=head;
char s[30];
printf("enter the record to search \n");
scanf("%s",s);
while(t->next!=NULL)
{
	if(strcmp(t->name,s)==0)
	{
		printf("record found\n" );
		printf("%d\t%s",t->val,t->name);
		getch();
        return ;
	}	
	t=t->next;
}
printf("Record not found");
getch();
}
void sort()
{
	 int cmpl(char [],char []);
	char f[30],g[30];
	p *t,*t2;
	int i,temp;
    for(t=head;t!=NULL;t=t->next)
	   {
	 for(t2=t->next;t2!=NULL;t2=t2->next)
	   {
	   	strcpy(f,t->name);
	    strcpy(g,t2->name);
	    i=cmpl(f,g);
	    if(i==1)
	    {
	    strcpy(t->name,g);
	    strcpy(t2->name,f);
		temp=t->val;
		t->val=t2->val;
		t2->val=temp;	
		}
	}
}
printf("Records are \n");
t=head;
	while(t!=NULL)
	{
	strcpy(f,t->name);
	i=t->val;	
	printf("%d\t%s\n",i,f);	
	t=t->next;
}
}
int cmpl(char a[],char b[])
{
int i=0,n;
n=strlen(a);
for(i=0;i<n;i++)
{
if(a[i]!=b[i])
{
if(a[i]>b[i])
{
 return 1;
	}	
else 
{
return 0;
	}	
}
}
}
void delete1()
{
 p *t=head;
 p *t1;
 char b[30],f[30];
 int i;
 printf("Enter the Name to Be Deleted");
 scanf("%s",b);
 while(strcmp(t->name,b)!=0)	
 {
 	t1=t;
 	t=t->next; 	
 }
if(t==head)
{
	printf("the deleted record is %d %s",t->val,t->name);
	head=head->next;
		 } 	
else
   {  
  printf("the deleted record is %d %s",t->val,t->name);
  t1->next=t->next;
		 }	
   t=head;
   printf("the records after deleting is \n");
	while(t!=NULL)
	{
	strcpy(f,t->name);
	i=t->val;	
	printf("%d\t%s\n",i,f);	
	t=t->next;
}
write_data();
getch();	 
}
void add(char f[],int id,char g[]) 
{
 FILE *fp1;
 if(strcmp(g,"customer")==0)
 {  
 fp1=fopen("customer.txt","a");
  fprintf(fp1,"%d %15s\n",id,f);
 }
 else if(strcmp(g,"employee")==0)
 {
 fp1=fopen("emp.txt","a");
  fprintf(fp1,"%d %15s\n",id,f);
 }
 fclose(fp1);
}
void introduction()
 {
   printf("\n                                                                                          :::::::::::::::::::::::::::::::::::::");
   printf("\n                                                                                          ::                                 ::");
   printf("\n                                                                                          ::     @@@@@@@@@@@@@@@@@@@@@@@     ::");
   printf("\n                                                                                          ::     @                     @     ::");
   printf("\n                                                                                          ::     @      WELCOME TO     @     ::");
   printf("\n                                                                                          ::     @                     @     ::");
   printf("\n                                                                                          ::     @    VIJAYA HOTEL     @     ::");
   printf("\n                                                                                          ::     @                     @     ::");
   printf("\n                                                                                          ::     @     BANGALURU       @     ::");
   printf("\n                                                                                          ::     @@@@@@@@@@@@@@@@@@@@@@@     ::");
   printf("\n                                                                                          ::                                 ::");
   printf("\n                                                                                          :::::::::::::::::::::::::::::::::::::\n\n");
   printf("\n\t                                                             Near Whitefield Field Main Road ,Opposite to Park Avenue,\n\t\t\t\t\t\t\t\t\t Bangaluru 560087, INDIA");
   printf("\n\n                                                                                               Ph. No.:080-272999789");
   printf("\n\n\n                                                                                        WELCOMES YOU..............");
   printf("\n\n\n\tHotel Vijaya is one of the finest  Hotel in Itpl Side. The Hotel is equipped with with all the general amenities and facilities that go along with memorable stay. Set amidst beautifully landscaped \n\tgardens, it proves to be a ideal dream destination for a avivd traveller.");
   printf("\n\n\tThe Hotel have well furnished rooms along with rooms providing pleasent views of the nature . The hotel satisfies the needs of business as well as the leisure traveller. \n\tAll the rooms at the hotel are furnished beautifully. All the rooms are fitted with amenities .");
   printf("\n\n                             AMENITIES .......\n");
   printf("\n\t\t\t1. 100%% Power backup.\n");
   printf("\t\t\t2. Automatic lift.\n");
   printf("\t\t\t3. Ample parking space.\n");
   printf("\t\t\t4. Round the clock security.\n");
   printf("\t\t\t5. Running hot and cold water.\n");
   printf("\t\t\t6. High speed internet service 24/7 .\n");
   printf("\t\t\t7. 24 hours room service.\n");
   printf("\t\t\t8. Laundary service.\n");
   printf("\t\t\t9.Bussiness Lounge\n");
   printf("\nPress any character to continue:");
 }
 void screenheader()
 {
   printf("\n                                                                                                                          ");
   printf("\n                                                                                              @@@@@@@@@@@@@@@@@@@@@@@     ");
   printf("\n                                                                                              @                     @     ");
   printf("\n                                                                                              @    VIJAYA HOTEL     @     ");
   printf("\n                                                                                              @                     @     ");
   printf("\n                                                                                              @     BANGALURU       @     ");
   printf("\n                                                                                              @@@@@@@@@@@@@@@@@@@@@@@     ");	
 		
 }
 
 int room()
 {
 	int p,r,typ,ch,n,a,i;
   screenheader();
   do
   {
   printf("\nChoose the room type:\n1. Super. Delux\n2. Delux");
   printf("\n3. General\n4. Couple\n5. Couple. Delux\n");
   scanf("%d",&typ);
   switch(typ)
     {
       case 1:system("cls");
       screenheader();
       if(c1==5)
       {
       	printf("room not available");
       	break;
	   }
       printf("\n Room number            >>>1");
       printf("\n Advance                >>>1000\n\n");
       printf("\n                      FEATURES OF THIS ROOM                       ");
       printf("\n------------------------------------------------------------------");
       printf("\n\n Room Type            >>> Super.delux");
       printf("\n\n Room charges         >>> Rs.3500 per day");
       printf("\n\n 1. Bed               >>>      3");
       printf("\n\n 2.Capacity           >>>      7");
       printf("\n\n 3.Balcony available     ");
       printf("\n------------------------------------------------------------------");
       printf("\n                     ADDITIONAL FEATURES                        ");
       printf("\n------------------------------------------------------------------");
       printf("\n\n 1.A/C  available ");
       printf("\n\n 2.Geyser available");
       printf("\n\n 3.TV available      ");
       printf("\n------------------------------------------------------------------");
       printf("\n NOTE :- Extra bed will cost Rs.300 per bed ");
       printf("\nenter the  no of beds and no of days\n");
       scanf("%d%d",&r,&i);
       p=1000+r*300;
       z=3500*i;
       c1++;
	   return p; 
       case 2:system("cls");
       screenheader();
        if(c2==5)
       {
       	printf("room not available");
       	break;
	   }
       printf("\n Room number            >>>2\n\n");
       printf("\n Advance                >>>900\n\n");
       printf("\n                      FEATURES OF THIS ROOM                       ");
       printf("\n-------------------------------------------------------------------");
       printf("\n\n Room Type            >>> Delux                                      ");
       printf("\n\n Room charges         >>>Rs.1800 per day");
       printf("\n\n 1. Bed               >>>      2");
       printf("\n\n 2.Capacity           >>>      4");
       printf("\n-------------------------------------------------------------------");
       printf("\n                    ADDITIONAL FEATURES                        ");
       printf("\n-------------------------------------------------------------------");
       printf("\n\n 1.A/C available   ");
       printf("\n\n 2.Geyser available");
       printf("\n\n 3.TV available      ");
       printf("\n-------------------------------------------------------------------");
       printf("\n NOTE :- Extra bed will cost Rs.300 per bed ");
       printf("\nenter the  no of beds and no of days\n");
       scanf("%d%d",&r,&i);
       p=900+r*300;
	   z=1800*i;
	   c2++;
	   return p;
       case 3:system("cls");
       screenheader();
        if(c3==5)
       {
       	printf("room not available");
       	break;
	   }
       printf("\n Room number            >>>3\n\n");
       printf("\n Advance                >>>300\n\n");
       printf("\n                      FEATURES OF THIS ROOM                       ");
       printf("\n-------------------------------------------------------------------");
       printf("\n\n Room Type            >>> General                                    ");
       printf("\n\n Room charges         >>>Rs.750 per day");
       printf("\n\n 1. Bed               >>>      1");
       printf("\n\n 2.Capacity           >>>      2");
       printf("\n-------------------------------------------------------------------");
       printf("\n                    ADDITIONAL FEATURES                        ");
       printf("\n-------------------------------------------------------------------");
       printf("\n\n 1.Geyser available      ");
       printf("\n-------------------------------------------------------------------");
       printf("\n NOTE :- Extra bed will cost Rs.200 per bed ");
       printf("\nenter the  no of beds and no of days\n");
       scanf("%d%d",&r,&i);
       p=300+r*200;
       z=750*i;
       c3++;
	   return p;
       case 4:system("cls");
       screenheader();
        if(c4==5)
       {
       	printf("room not available");
       	break;
	   }
       printf("\n Room number            >>>4\n\n");
       printf("\n Advance                >>>300\n\n");
       printf("\n                      FEATURES OF THIS ROOM                       ");
       printf("\n-------------------------------------------------------------------");
       printf("\n\n Room Type            >>> Couple                                     ");
       printf("\n\n Room charges         >>>Rs.1000 per day");
       printf("\n\n 1. Bed               >>>      1");
       printf("\n\n 2.Capacity           >>>      2");
       printf("\n-------------------------------------------------------------------");
       printf("\n                    ADDITIONAL FEATURES                        ");
       printf("\n-------------------------------------------------------------------");
       printf("\n\n 1.Geyser available");
       printf("\n\n 2.TV available      ");
       printf("\n-------------------------------------------------------------------");
       printf("\n NOTE :- Extra bed will cost Rs.200 per bed ");
       printf("\nenter the  no of beds and no of days\n");
       scanf("%d%d",&r,&i);
       p=300+r*200;
       z=1000*i;
       c4++;
	   return p;
	   
       case 5:system("cls");
       screenheader();
        if(c5==5)
       {
       	printf("room not available");
       	break;
	   }
       printf("\n Room number            >>>5\n\n");
       printf("\n Advance                >>>850\n\n");
       printf("\n                      FEATURES OF THIS ROOM                       ");
       printf("\n-------------------------------------------------------------------");
       printf("\n\n Room Type            >>> Couple Delux                                    ");
       printf("\n\n Room charges         >>>Rs.3500 per day");
       printf("\n\n 1. Bed               >>>      1");
       printf("\n\n 2.Capacity           >>>      2");
       printf("\n-------------------------------------------------------------------");
       printf("\n                    ADDITIONAL FEATURES                        ");
       printf("\n-------------------------------------------------------------------");
       printf("\n\n 1.A/C available   ");
       printf("\n\n 2.Geyser available");
       printf("\n\n 3.TV available      ");
       printf("\n-------------------------------------------------------------------");
       printf("\n NOTE :- Extra bed will cost Rs.300 per bed ");
       printf("\nenter the  no of beds and no of days\n");
       scanf("%d%d",&r,&i);
       p=850+r*300;
       z=3500*i;
       c5++;
	   return p;
     } 
	 }while(1); 
   }
   int passwd()
{
	int c,l=0;
	char a[10],b[10];
    strcpy(b,"test1");
	printf("Enter the password for admin\n");
	printf("Enter password here");
	while(1)
{
	a[l]=getch();
	c=a[l];
	if(c==8)	
	{
	l--;
	putchar('\b');
	putchar(' ');
	putchar('\b');
	continue;
	}
else if(c==13)	
{
	break;
}
else
   { printf("*");
     l++; 
   }
}
a[l]='\0';
printf("\nthe password is %s\n",a);
if(strcmp(b,a)==0)
{
	printf("\n\nACCESS GRANTED");
	return 1;
}
else
{printf("\n\nINCORRECT PASSWORD");
return 0;
}
}
void menu()
{
int price[50]={256,240,265,270,255,
   255,240,240,235,220,25,30,25,30,35,35,25,30,35,25,35,25,25,30,100,105,105,
   100,105,100,105,125,105,105,100,105,110,115,100,100,100,105,105,105,105,
   125,105,120,120,100};
   char food[50][30]={
   "CHICKEN TANDOORI(1/2)","PANEER TIKKA","CHICKEN SEEKH KABAB",
   "CHICKEN HARA KABAB","CHICKEN BIRYANI","MUTTON BIRYANI","PANEER PULAO",
   "VEG.PULAO","JEERA RICE","STEAMED RICE","RUMALI ROTI","ROTI","NAN",
   "ALOO NAN","PANEER NAN","KEEMA NAN","PARANTHA","ALOO PARANTHA",
   "PANEER PARANTHA","PUDINA PARANTHA","BUTTER NAN","LACHCHA PARANTHA",
   "MISSI ROTI","KHASTA ROTI","VEG.BURGER","PANEER BURGER","CHEESE SANDWICH",
   "VEG.PATTI","CHICKEN PATTI","TEA","COFFEE","COLD COFFEE","PINEAPPLE",
   "STRAWBERRY","CHOCOLATE","BLACK FOREST","DOUBLE STORIED","TRIPLE STORIED",
   "SOFT CONE","VANILLA","STRAWBERRY","CHOCOLATE","CHOCO CHIPS","MANGO",
   "TUTTI FRUITY","LICHI","PISTA BADAM","CHOCOLATE PISTA BADAM","CHOCO DIP",
   "CHOCOLATE LICHI"};
int i;
   screenheader();
   printf("\n\t\t\t                        *********");
   printf("\n\t\t\t                        MENU CARD");
   printf("\n\t\t\t                        *********");
   printf("\n\tPrice\t\tName");
for(i=0;i<50;i++)
{
printf("\n\t%d\t\t%s",price[i],food[i]);
}
getch();
}
int main()
{ 
    int ch,re,eid,i,c;
    p *temp;
    char en[30];
	printf("\n\n\n\n\n\n\n\n\n\n\n");
	printf("\t\t\t\t\t\tH   H     O O  TTTTTTTT  EEEEEEEEE   L                             \n");             
	printf("\t\t\t\t\t\tH   H    O   O     T     E           L                 \n");
	printf("\t\t\t\t\t\tHHHHH   O    O     T     EEEEEEEEE   L                \n");
	printf("\t\t\t\t\t\tH   H    O  O      T     E           L           \n");
	printf("\t\t\t\t\t\tH   H     O        T     EEEEEEEEE   LLLLLLLL                  \n");   
    printf("\t\t\t M        M      A         N    N         A         GGGGGGGG  EEEEEEE   M        M   EEEEEE   N     N    TTTTTTT \n");             
	printf("\t\t\t M   M  M M     A  A       N N  N        A  A       G         E         M   M  M M   E        N N   N       T    \n");
	printf("\t\t\t M    M   M    AAAAAA      N  N N       AAAAAA      G    GGG  EEEEEEE   M        M   EEEEEE   N  N  N       T    \n");
	printf("\t\t\t M        M   A      A     N   NN      A       A    G      G  E         M        M   E        N    NN       T    \n");
	printf("\t\t\t M        M  A        A    N    N     A         A   GGGGGGGG  EEEEEEE   M        M   EEEEEE   N     N       T    \n");   
	printf("\n\n\n\n\n\n\n\n\t\t\t\tPress Enter");
	getch();
	system("cls");
    introduction();
    getch();
    do
    {
    system("cls");	
    screenheader();
    printf("\nEnter 1-admin\n2-User");
    scanf("%d",&c);
    system("cls");
    if(c==1)
   {
    re=passwd();
    if(re==1)
    {
    system("cls");	
    screenheader();
	printf("\nEnter the choice \n 1-Display records\n2-Delete\n3-Add employee\n4-search for employee\n");
	scanf("%d",&i);
	system("cls");
	if(i==1)
	{
	screenheader();
	insert();
    sort();
    getch();
	}
	else if(i==2)
	{
	screenheader();
	insert();
	delete1();	
	}
	else if(i==3)
	{
	screenheader();	
	printf("enter the employee name and id");
	scanf("%s%d",en,&eid);	
	add(en,eid,"employee");
	}
	else
	{
		insert();
		search();
	}
	}
	else 
	{
      break;
 	}
	
}
	else if(c==2)
	{
    printf("\nEnter the choice \n 1-New User\n2-Old user\n ");
    scanf("%d",&i);
	if(i==1)
    {  
    system("cls");	
    screenheader();
    ch=room();
    printf("The total cost to be paid upfront %d\n ",ch);
    printf("Enter the name of user");
    scanf("%s",en);
    add(en,z,"customer");
	printf("Do you want to see the menu 1-yes\t2-no\n ");
	scanf("%d",&ch);
	if(ch==1)
	{
	    system("cls");	
	menu();
	}
}
	else
	{
		    system("cls");
		menu();
	}
	}
}while(c<3);
getch();
return 1;
}

