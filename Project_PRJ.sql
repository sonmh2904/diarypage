use [master]
GO

create database[Project_PRJ]
GO

use [Project_PRJ]
GO
CREATE TABLE Users(
	account nchar(10) NOT NULL PRIMARY KEY,
	password nchar(10) NOT NULL,
	name nvarchar(50) NULL,
	gender bit NULL,
	address nvarchar(50) NULL,
	dateofbirth date NULL,
	role bit NOT NULL default 0,
	status bit NOT NULL default 0,
)

create table Category(
	cid int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	name VARCHAR(20) NOT NULL,
	
)



CREATE TABLE Posts (
  pid INT IDENTITY(1,1) NOT NULL,
  account nchar(10) NOT NULL,
  describe text,
  content TEXT NOT NULL,
  post_date DATE NOT NULL,
  status int NOT NULL,
  PRIMARY KEY (pid),
  FOREIGN KEY (account) REFERENCES Users(account)
)


create table postCategory(
	cid int NOT NULL REFERENCES Category(cid),
	pid int NOT NULL REFERENCES Posts(pid)
)

CREATE TABLE Comments (
  cmid INT IDENTITY(1,1) NOT NULL,
  pid INT NOT NULL,
  account nchar(10) NOT NULL,
  content TEXT NOT NULL,
  cm_date DATETIME NOT NULL,
  PRIMARY KEY (cmid),
  FOREIGN KEY (account) REFERENCES Users(account),
  FOREIGN KEY (pid) REFERENCES Posts(pid)
);



insert into Users (account, password, name, gender, address, dateofbirth,role,status)
values
('admin', '123',N'Phạm Văn Cầu', 1, N'Ninh Bình','2002-09-30',1,0),
('cau1', '123',N'Phạm Văn A', 1, N'Ninh Bình','2002-10-30',0,0),
('cau2', '123',N'Phạm Văn B', 0, N'Hà Nội','2002-11-30',0,0),
('cau3', '123',N'Phạm Văn C', 1, N'Hà Nam','2002-12-30',0,0),
('cau4', '123',N'Phạm Văn D', 0, N'Quảng Ninh','2002-12-30',0,0)

insert into Posts ( account,describe, content, post_date, status)
values
('cau1','describe1','.....','2023-03-05',0),
('cau3','describe2','.....','2023-03-01',1),
('cau2','describe3','.....','2023-03-03',0),
('cau2','describe4','.....','2023-03-04',0),
('cau4','describe5','.....','2023-03-02',1)

insert into Category (name)
values
('Family'),
('Love'),
('Study'),
('Movie'),
('Sport'),
('Other')

insert into postCategory (cid,pid)
values
(1,2),
(2,3),
(3,5),
(4,1)

insert into Comments(pid, account, content, cm_date)
values
(1,'cau2','....','2023-03-18 5:48:20'),
(2,'cau1','....','2023-03-19 15:50:21'),
(3,'cau2','....','2023-03-20 5:11:22'),
(4,'cau4','....','2023-03-22 15:23:23'),
(4,'cau3','....','2023-02-22 5:33:24'),
(4,'cau2','....','2023-02-22 15:44:25'),
(4,'cau1','....','2023-02-22 5:55:26')
