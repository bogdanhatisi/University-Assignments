USE [ProjectManager]
GO
/****** Object:  StoredProcedure [dbo].[down_to_version_3]    Script Date: 11/29/2022 2:02:25 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[down_to_version_3]
	-- Add the parameters for the stored procedure here
AS
BEGIN
	
	ALTER TABLE UpcomingProject
	DROP COLUMN Budget

END
