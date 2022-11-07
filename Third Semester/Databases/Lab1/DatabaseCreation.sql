CREATE DATABASE ProjectManager4
go
USE ProjectManager4
go


CREATE TABLE Employee
(Eid INT PRIMARY KEY IDENTITY,
EName varchar(50),
Erole varchar(50),
Age int,
Experience varchar(50)
)

CREATE TABLE Clients
(Cid INT PRIMARY KEY IDENTITY,
Cname varchar(50),
Note varchar(100),
Email varchar(50)
)

CREATE TABLE Project
(Pid INT PRIMARY KEY IDENTITY,
Pname varchar(50),
Pdescription varchar(100),
Cid INT FOREIGN KEY REFERENCES Clients(Cid)
)

CREATE TABLE Tasks
(Tid INT PRIMARY KEY IDENTITY,
Tname varchar(50),
Deadline int,
TDescription varchar(100),
Eid INT FOREIGN KEY REFERENCES Employee(Eid),
Pid INT FOREIGN KEY REFERENCES Project(Pid)
)

CREATE TABLE Manager
(Mid INT PRIMARY KEY IDENTITY,
Mname varchar(50),
Responsability varchar(100),
Pid INT FOREIGN KEY REFERENCES Project(Pid)
)


CREATE TABLE ManagerInfo
(ContactNumber int,
ManagerDescription varchar(50),
ExpertIn varchar(50),
Mid INT FOREIGN KEY REFERENCES Manager(Mid),
CONSTRAINT pk_ManagerInfo PRIMARY KEY (Mid)
)

CREATE TABLE BugTickets
(BugId INT PRIMARY KEY IDENTITY,
BugName varchar(50),
BugDescription varchar(50),
isSolved bit,
Pid INT FOREIGN KEY REFERENCES Project(Pid)
)


CREATE TABLE ClientInfo
(ContactNumber int,
ClientDescription varchar(50),
ClientPriority varchar(50),
Country varchar(50),
NumberOfProjects int,
Cid INT FOREIGN KEY REFERENCES Clients(Cid),
CONSTRAINT pk_ClientInfo PRIMARY KEY (Cid)
)

CREATE TABLE EmployeeInfo
(ContactNumber int,
EmployeeDescription varchar(50),
EmployeeHistory varchar(50),
PreviousProject varchar(50),
YearsOfExperience int,
Eid INT FOREIGN KEY REFERENCES Employee(Eid),
CONSTRAINT pk_EmployeeInfo PRIMARY KEY (Eid)
)

CREATE TABLE Budget
(Budget int,
Flexibility varchar(100),
Pid INT FOREIGN KEY REFERENCES Project(Pid),
CONSTRAINT pk_ProjectBudget PRIMARY KEY (Pid)
)