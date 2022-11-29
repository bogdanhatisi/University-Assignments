USE [ProjectManager]
GO
/****** Object:  StoredProcedure [dbo].[up_to_version_4]    Script Date: 11/29/2022 1:57:47 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[up_to_version_4]
	-- Add the parameters for the stored procedure here
AS
BEGIN
	
	ALTER TABLE UpcomingProject
	ADD Budget int

END
