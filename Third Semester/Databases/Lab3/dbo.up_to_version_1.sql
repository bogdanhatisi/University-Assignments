USE [ProjectManager]
GO
/****** Object:  StoredProcedure [dbo].[up_to_version_1]    Script Date: 11/29/2022 1:56:35 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[up_to_version_1]

AS
BEGIN
	
	ALTER TABLE Employee
	ADD YearsOfExperience varchar(50)

	ALTER TABLE Employee
	ALTER COLUMN YearsOfExperience int

	   	 	
END
