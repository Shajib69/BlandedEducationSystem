<%@page import="java.util.Date"%>
<%@include file="header.jsp" %>
<div id="page-wrapper" style="min-height: 800px;">
    <div class="container-fluid">
        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">
                    <i class="fa fa-file"></i> Course
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

        <div class="row">
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <c:if test="${course.courseId != null}">
                                    <h4>Edit Course</h4>
                                </c:if>
                                <c:if test="${course.courseId == null}">
                                    <h4>Create Course</h4>
                                </c:if>
                            </div>
                            <div class="panel-body">
                                <c:if test="${course.courseId == null}">
                                    <form action="${pageContext.request.contextPath}/admin/saveCourse" method="post">
                                    </c:if>
                                    <c:if test="${course.courseId != null}">
                                        <form action="${pageContext.request.contextPath}/admin/updateCourse" method="post">
                                        </c:if>
                                        <c:if test="${course.courseId != null}">
                                            <div class="form-group">
                                                <label class="control-label">Course Id:</label>
                                                <input value="${course.courseId}" readonly="1" name="courseId" type="text" class="form-control"/>
                                            </div>
                                        </c:if>

                                        <div class="form-group">
                                            <label class="control-label">Course Code:</label>

                                            <input value="${course.courseCode}"  name="courseCode" type="text" class="form-control" id="courseName" placeholder="Enter Course Code"/>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="courseName">Course Name:</label>
                                            <input value="${course.courseName}"  name="courseName" type="text" class="form-control" id="courseName" placeholder="Enter Course Name"/>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Description</label>
                                            <textarea class="form-control" name="description">${course.description}</textarea>
                                        </div>
                                        <c:if test="${course.courseId == null}">
                                            <div class="form-group"> 
                                                <div class="checkbox">
                                                    <label><input type="checkbox" name="status"> Active</label>
                                                </div>
                                            </div>
                                        </c:if>
                                        <div class="form-group">
                                            <c:if test="${course.courseId == null}">
                                                <button type="submit" class="btn btn-success">Create</button>
                                            </c:if>
                                            <c:if test="${course.courseId != null}">
                                                <button type="submit" class="btn btn-success"><i class="fa fa-edit"></i> Update</button>
                                            </c:if>
                                            <button type="reset" class="btn btn-default">Reset</button>
                                            <c:if test="${course.courseId != null}">
                                                <a href="${pageContext.request.contextPath}/admin/add_course" class="btn btn-warning pull-right"><i class="fa fa-refresh"></i> New</a>
                                            </c:if>
                                        </div>
                                    </form>
                            </div>
                            <div class="panel-footer"></div>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4>Upload Course Book</h4>
                            </div>
                            <div class="panel-body">

                                <form method="POST" action="<%= request.getContextPath()%>/admin/fileUpload" enctype="multipart/form-data">

                                    <select name="courseId" class="form-control">
                                        <option value="0">--- select course ---</option>
                                        <c:forEach var="row" items="${courses}">
                                            <option value="${row.courseId}">${row.courseName}</option>
                                        </c:forEach>
                                    </select>

                                    <!--<input type="file" name="file">-->

                                    <div class="input-group" style="margin-top: 10px;">
                                        <label class="input-group-btn">
                                            <span class="btn btn-primary">
                                                Browse&hellip; <input accept=".pdf" type="file" name="file" style="display: none;" multiple>
                                            </span>
                                        </label>
                                        <input type="text" class="form-control" readonly>
                                    </div>
                                    <!--                                    <span class="help-block">
                                                                            Try selecting one or more files and watch the feedback
                                                                        </span>-->
                                    <button type="submit" class="btn btn-success" style="width: 100%; margin-top: 10px;"><i class="fa fa-floppy-o"></i> Save</button>

                                </form>	

                            </div>
                        </div>
                    </div>
                </div>
            </div>

            




        </div>





    </div>
</div>

<%@include file="footer.jsp" %>