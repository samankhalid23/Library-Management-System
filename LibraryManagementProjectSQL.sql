CREATE PROCEDURE UPD_FINE_STATUS(@STATUS CHAR(10),@ISSUEID CHAR(10),@MEMBERID CHAR(10))
AS
DECLARE @IS_ID CHAR(10)
DECLARE @MM_ID CHAR(10)
SET @IS_ID  = @ISSUEID
SET @MM_ID  = @MEMBERID
UPDATE ISSUANCE_FINE SET FINESTATUS = @STATUS WHERE ISSUEID = @IS_ID AND MEMBERID = @MM_ID
GO
EXECUTE UPD_FINE_STATUS @STATUS = 'PAID', @ISSUEID = 'IS2022004 ', @MEMBERID = 'MEM202203'
CREATE TRIGGER FINING
ON RETURN_BOOK
AFTER INSERT
AS
DECLARE @RTDATE DATE
DECLARE @DUEDATE DATE
DECLARE @I_ID CHAR(10)
DECLARE @M_ID CHAR(10)
SELECT @DUEDATE =  b.IssueDueDate from book_issuance as b inner join inserted as i on b.IssueID = i.IssueID
SELECT @RTDATE =  ReturnDate from Inserted 
SELECT @I_ID  =  IssueID from Inserted
SELECT @M_ID  =  memberid from Inserted
IF @RTDATE>@DUEDATE
BEGIN
INSERT INTO ISSUANCE_FINE VALUES (@I_ID,@M_ID,$10,'UNPAID')
END
GO


CREATE PROCEDURE UNISSUED_BOOKS
AS
SELECT B.* FROM BOOK AS B LEFT OUTER JOIN BOOK_ISSUANCE AS I ON B.BOOKISBN = I.BOOKISBN WHERE I.MEMBERID IS NULL
GO
EXECUTE UNISSUED_BOOKS

CREATE PROCEDURE DATE_EXT(@ISSUEID CHAR(10))
AS
DECLARE @ID CHAR(10);
SET @ID = @ISSUEID;
UPDATE BOOK_ISSUANCE SET ISSUEDUEDATE = DATEADD(DD,5,ISSUEDUEDATE) WHERE ISSUEID = @ID;
GO

EXECUTE DATE_EXT @ISSUEID = 'IS2022000'


Create table Book
(
BookISBN		CHAR(17) PRIMARY KEY NOT NULL,
BookTitle		VARCHAR(100),
BookAuthor		VARCHAR(100),
BookPublisher	VARCHAR(100),
BookLanguage	CHAR(40),
BookGenre		VARCHAR(40),
BookEdition		VARCHAR(10),
BookPages		INTEGER,
)

Create table Faculty
(
LibID			CHAR(10) PRIMARY KEY NOT NULL,
LibFirstName	CHAR(30),
LibLastName		CHAR(30),
LibEmail		CHAR(30),
LibPhone		CHAR(20),
LibAddress		VARCHAR(200),
Supervisor		CHAR(10), 
CONSTRAINT Fkf_supervisor FOREIGN KEY(Supervisor) REFERENCES Faculty(LibID)
)

Create Table Member
(
MemberID			CHAR(10) PRIMARY KEY NOT NULL,
MemberFName			CHAR(30),
MemberLName			CHAR(30),
MemberPhone			CHAR(20),
MemberEmail			CHAR(30),
MemberAddress		VARCHAR(200),
MemberZipCode		CHAR(10),
)

Create Table Book_Issuance
(
IssueID				CHAR(10),
IssueDate			DATE,
IssueDueDate		DATE,
MemberID			CHAR(10),
BookISBN			CHAR(17),
CONSTRAINT Fki_bookisbn FOREIGN KEY (BookISBN) REFERENCES Book(BookISBN),
CONSTRAINT Fki_memberid FOREIGN KEY (MemberID) REFERENCES Member(MemberID),
PRIMARY KEY(MemberID,IssueID)
)

Create Table Issuance_Fine
(
	IssueID				CHAR(10),
	MemberID			CHAR(10),
	FinedAmount			INTEGER,
	FineStatus			CHAR(10),
	CONSTRAINT Fkf_memberid FOREIGN KEY (MemberID,IssueID) REFERENCES Book_Issuance(MemberID,IssueID),
	PRIMARY KEY(MemberID,IssueID)
)

Create Table Return_Book
(
	IssueID				CHAR(10),
	MemberID			CHAR(10),
	ReturnDate			DATE,
	CONSTRAINT Fkr_memberid FOREIGN KEY (MemberID,IssueID) REFERENCES Book_Issuance(MemberID,IssueID),
	PRIMARY KEY(MemberID,IssueID)
)

Create Table manages
(
BookISBN		CHAR(17),
LibID			CHAR(10),
CONSTRAINT Fkm_libid FOREIGN KEY (LibID) REFERENCES Faculty(LibID),
CONSTRAINT Fkm_bookisbn FOREIGN KEY (BookISBN) REFERENCES Book(BookISBN),
PRIMARY KEY (BookISBN,LibID)
)

Create Table oversee
(
MemberID		CHAR(10),
LibID			CHAR(10),
CONSTRAINT Fko_libid FOREIGN KEY (LibID) REFERENCES Faculty(LibID),
CONSTRAINT Fko_memberid FOREIGN KEY (MemberID) REFERENCES Member(MemberID),
PRIMARY KEY(MemberID,LibID)
)
INSERT INTO BOOK VALUES
('0-06-083281-9','The Zahir','Paulo Coelho','Harper Perennial','English','Philosophy','1st',336),
('978-0-545-61494-8','The Selection','Kiera Cass','HarperCollins','English','Dystopia','1st',328),
('978-0-470-45836-5','Advanced Engineering Mathematics','Erwin Kreyszig','Pearson Publishers','English','Educational','9th',1299),
('997-8-009-95415-3','The Great Gatsby','F.Scott Fitzgerald','Barnes and Noble','English','Romance','1st',345),
('978-1-623-65520-4','The Duke Agent','Rebecca Jenkins','Quercus','English','Mystery','1st',312),
('978-0-141-43951-8','Pride and Prejudice','Jane Austen','Penguin Books','English','Romance','1st',289),
('978-1-400-07998-8','War and Peace','Leo Tolstoy','Penguin Books','English','Philosphy','1st',563),
('978-0-137-39918-5','Thomas Calculas','George B.Thomas','Pearson Publishers','English','Educational','1st',1396),
('978-1-594-63366-9','The Girl on the Train','Paula Hawkins','Riverhead US','English','Mystery','1st',276),
('978-0-486-41587-1','Crime and Punishment','Fyodor Dostoyevsky','The Russian Messenger','Russian','Philosophy','1st',457)

INSERT INTO FACULTY VALUES
('LIB202201','Hareem','Ahmed','h.ahmed111@gmail.com','033370000','House 11/Z,21st Street,RWP,PK',NULL),
('LIB202202','Ariba','Arshad','ariba333@gmail.com','0333788888','House 18/E,23rd Street,RWP,PK','LIB202201'),
('LIB202203','Hadia','Shafqat','hadia200@gmail.com','032299999','House 12/X,28th Street,RWP,PK','LIB202201'),
('LIB202204','Saman','Khalid','saman4242@gmail.com','030055555','House 14/V,27th Street,RWP,PK','LIB202201')

INSERT INTO MEMBER VALUES
('MEM202201','Ali','Tahir','0322346514','a.tahir45@gmail.com','House 15/Z,21st Street,RWP,PK','46000'),
('MEM202202','Shazia','Khan','0338545242','s.khan@gmail.com','House 12/T,24th Street,RWP,PK','46000'),
('MEM202203','Tehmina','Shirazi','0331467854','tehmina12@gmail.com','House 18/B,19th Street,RWP,PK','47300'),
('MEM202204','Anwar','Hussain','0300247128','anwarh89@gmail.com','House 2/Z,11th Street,RWP,PK','46300'),
('MEM202205','Zohaib','Khan','0333912954','zohkhan34@gmail.com','House 19/F,11th Street,RWP,PK','47300'),
('MEM202206','Naila','Tariq','0320931347','n.tariq1200@gmail.com','House 20/F,11th Street,RWP,PK','46300'),
('MEM202207','Waseem','Ahmed','0333971285','w.ahmed1221@gmail.com','House 12/V,27th Street,RWP,PK','46300'),
('MEM202208','Raza','Hashim','0333457885','razahashim78@gmail.com','House 17/C,20th Street,RWP,PK','47300'),
('MEM202209','Nadia','Khan','0322539785','nadiak7888@gmail.com','House 15/A,21st Street,RWP,PK','46200'),
('MEM202210','Hasan','Zahir','0333978237','has_zahir1209@gmail.com','House 11/B,15th Street,RWP,PK','46200')

INSERT INTO BOOK_ISSUANCE VALUES
('IS2022000','12-Sep-2021','22-Sep-2021','MEM202203','978-1-594-63366-9'),
('IS2022001','3-June-2021','13-June-2021','MEM202201','978-0-470-45836-5'),
('IS2022002','10-Apr-2021','21-Apr-2021','MEM202204','978-1-400-07998-8'),
('IS2022003','19-Dec-2021','29-Dec-2021','MEM202202','997-8-009-95415-3'),
('IS2022004','15-Feb-2021','20-Jan-2021','MEM202203','978-0-486-41587-1')

INSERT INTO RETURN_BOOK VALUES
('IS2022000','MEM202203','22-Sep-2021')
INSERT INTO RETURN_BOOK VALUES
('IS2022004','MEM202203','24-Jan-2021')
INSERT INTO RETURN_BOOK VALUES
('IS2022002','MEM202204','18-Apr-2021')
SELECT * FROM Issuance_Fine