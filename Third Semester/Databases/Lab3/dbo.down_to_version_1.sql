USE [ProjectManager]
GO
/****** Object:  StoredProcedure [dbo].[down_to_version_1]    Script Date: 11/29/2022 2:01:05 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[down_to_version_1]
	-- Add the parameters for the stored procedure here
AS
BEGIN
	ALTER TABLE Employee
	DROP CONSTRAINT df_19

END
