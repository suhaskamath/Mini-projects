# Support Vector Machine (SVM)

# Importing the libraries
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd

# Importing the dataset
dataset = pd.read_csv('Mall_Customers.csv')
inp=pd.read_csv('inp.csv')
X_test=inp.iloc[:,].values
X_train = dataset.iloc[:,1:4].values
y_train = dataset.iloc[:, -1].values
for i in range(0,200):
    if X_train[i,0]=='Male':
        X_train[i,0]=0
    else:
        X_train[i,0]=1
if X_test[0,0]=='Male':
    X_test[0,0]=0
else:
    X_test[0,0]=1
# Splitting the dataset into the Training set and Test set
"""from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.0, random_state = 0)
"""



# Fitting SVM to the Training set
from sklearn.svm import SVC
classifier = SVC(kernel = 'linear', random_state = 0)
classifier.fit(X_train, y_train)

# Predicting the Test set results
y_pred = classifier.predict(X_test)


y_pred1=[]
"""
for i in range(0,50):
    if y_pred[i,]>=-50 and y_pred[i,]<=40:
        y_pred1.append('A')
    if y_pred[i,]>40 and y_pred[i,]<=65:
        y_pred1.append('B')
    if y_pred[i,]>65 and y_pred[i,]<=100:
        y_pred1.append('C')
y_test1=[]
for i in range(0,50):
    if y_test[i,]>=-50 and y_test[i,]<=40:
        y_test1.append('A')
    if y_test[i,]>40 and y_test[i,]<=65:
        y_test1.append('B')
    if y_test[i,]>65 and y_test[i,]<=100:
        y_test1.append('C')
count=0
for i in range(0,50):
    if y_test1[i]==y_pred1[i]:
        count=count+1;
print(count/50)
"""
i=0
if y_pred[i,]>=0 and y_pred[i,]<=40:
    y_pred1.append('C')
if y_pred[i,]>40 and y_pred[i,]<=65:
    y_pred1.append('B')
if y_pred[i,]>65 and y_pred[i,]<=100:
    y_pred1.append('A')
y_pred1.append(y_pred[0])
df = pd.DataFrame({'col':y_pred1})
df.to_csv('op.csv', sep=',')