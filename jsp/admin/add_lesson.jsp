<%@page import="java.util.Date"%>
<%@include file="header.jsp" %>
<div id="page-wrapper" style="min-height: 800px;">
    <div class="container-fluid">
        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">
                    <i class="fa fa-file"></i> Lesson
                </h2>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <c:if test="${sm != null}">
                    <div class="alert alert-success alert-dismissable">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>Success!</strong> ${sm}
                    </div>
                </c:if>
                <c:if test="${em != null}">
                    <div class="alert alert-danger alert-dismissable">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>Error!</strong> ${em}
                    </div>
                </c:if>
            </div>
        </div>


        <div class="row" style="margin-top: 20px;">
            <div class="col-md-4">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4>Create Lesson</h4>
                    </div>
                    <div class="panel-body">
                        <form action="${pageContext.request.contextPath}/admin/saveLesson" method="post">
                            <div class="form-group">
                                <label class="control-label">Select Course:</label>
                                <select name="courseId" class="form-control">
                                    <option>-- select course --</option>
                                    <c:forEach var="row" items="${courses}">
                                        <option value="${row.courseId}">${row.courseName}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label class="control-label">Lesson Title:</label>
                                <input  name="lessonTitle" type="text" class="form-control" id="lessonTitle" placeholder="Enter Lesson Title"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Description</label>
                                <textarea class="form-control" name="description"></textarea>
                            </div>
                            <div class="form-group"> 
                                <div class="checkbox">
                                    <label><input type="checkbox" name="status"> Active</label>
                                </div>
                            </div>
                            <div class="form-group"> 
                                <button type="submit" class="btn btn-success">Create</button>
                                <button type="reset" class="btn btn-default">Reset</button>
                            </div>
                        </form>
                    </div>
                    <div class="panel-footer"></div>
                </div>
            </div>



            <div class="col-md-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4>Upload Presentation</h4>
                    </div>
                    <div class="panel-body">
                        <form action="${pageContext.request.contextPath}/admin/uploadPPT" method="post" enctype="multipart/form-data">
                            <div class="form-group">
                                <label class="control-label">Select Lesson</label>
                                <select name="lessonId" class="form-control">
                                    <option>-- select Lesson --</option>
                                    <c:forEach var="row" items="${lessons}">
                                        <option value="${row.lessonId}">${row.lessonTitle}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="input-group">
                                <span class="input-group-btn">
                                    <button id="fake-file-button-browse" type="button" class="btn btn-default">
                                        <span class="glyphicon glyphicon-file"></span>
                                    </button>
                                </span>
                                <input accept=".pptx" name="file" type="file" id="files-input-upload" style="display:none">
                                <input type="text" id="fake-file-input-name" disabled="disabled" placeholder="File not selected" class="form-control">
                                <span class="input-group-btn">
                                    <button type="submit" class="btn btn-default" disabled="disabled" id="fake-file-button-upload">
                                        <span class="glyphicon glyphicon-upload"></span>
                                    </button>
                                </span>
                            </div>
                        </form>


                    </div>
                </div>
            </div>



            <div class="col-md-4">
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <h4>Upload Notes</h4>
                    </div>
                    <div class="panel-body">
                        <form action="${pageContext.request.contextPath}/admin/uploadNotes" method="post" enctype="multipart/form-data">
                            <div class="form-group">
                                <label class="control-label">Select Lesson</label>
                                <select name="lessonId" class="form-control">
                                    <option>-- select Lesson --</option>
                                    <c:forEach var="row" items="${lessons}">
                                        <option value="${row.lessonId}">${row.lessonTitle}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="input-group" style="margin-top: 10px;">
                                <label class="input-group-btn">
                                    <span class="btn btn-primary">
                                        Browse&hellip; <input accept=".pdf" type="file" name="file" style="display: none;" multiple>
                                    </span>
                                </label>
                                <input type="text" class="form-control" readonly>
                            </div>

                            <button type="submit" class="btn btn-success" style="width: 100%; margin-top: 10px;"><i class="fa fa-floppy-o"></i> Save</button>
                        </form>


                    </div>
                </div>
            </div>
        </div>










    </div>
</div>

<%@include file="footer.jsp" %>