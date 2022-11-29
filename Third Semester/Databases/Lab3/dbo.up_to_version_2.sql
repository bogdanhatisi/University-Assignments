USE [ProjectManager]
GO
/****** Object:  StoredProcedure [dbo].[up_to_version_2]    Script Date: 11/29/2022 1:56:58 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[up_to_version_2]
	-- Add the parameters for the stored procedure here
	AS
BEGIN
	
	ALTER TABLE Employee
	ADD CONSTRAINT df_19 DEFAULT 19
	FOR Age
	
	
END

