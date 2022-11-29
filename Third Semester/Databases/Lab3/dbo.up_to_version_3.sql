USE [ProjectManager]
GO
/****** Object:  StoredProcedure [dbo].[up_to_version_3]    Script Date: 11/29/2022 1:57:31 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[up_to_version_3]
	-- Add the parameters for the stored procedure here
AS
BEGIN
	
	CREATE TABLE UpcomingProject(
	Upid int PRIMARY KEY,
	Upname varchar(50),
	Updescription varchar(50),
	)

END
