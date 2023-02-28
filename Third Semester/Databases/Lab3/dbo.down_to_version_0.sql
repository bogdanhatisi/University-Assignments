USE [ProjectManager]
GO
/****** Object:  StoredProcedure [dbo].[down_to_version_0]    Script Date: 11/29/2022 2:01:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[down_to_version_0]

AS
BEGIN
	

	ALTER TABLE Employee
	ALTER COLUMN YearsOfExperience int NOT NULL

END	