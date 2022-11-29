USE [ProjectManager]
GO
/****** Object:  StoredProcedure [dbo].[up_to_version_5]    Script Date: 11/29/2022 1:57:51 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[up_to_version_5]
	-- Add the parameters for the stored procedure here
AS
BEGIN
	
	CREATE TABLE UpcomingEmployee(
	Ueid int PRIMARY KEY,
	Pid int CONSTRAINT fk_Upcoming_Employee FOREIGN KEY(Pid) REFERENCES Project(Pid)	
	)

END
