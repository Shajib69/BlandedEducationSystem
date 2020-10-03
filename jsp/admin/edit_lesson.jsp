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
                        <h4>Edit Lesson</h4>
                    </div>
                    <div class="panel-body">
                        <form action="${pageContext.request.contextPath}/admin/updateLesson" method="post">
                            <div class="form-group">
                                <input type="hidden" name="lessonId" value="${lesson.lessonId}"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Select Course:</label>
                                <select id="courseId" name="courseId" class="form-control">
                                    <option>-- select course --</option>
                                    <c:forEach var="row" items="${courses}">
                                        <option value="${row.courseId}">${row.courseName}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label class="control-label">Lesson Title:</label>
                                <input value="${lesson.lessonTitle}"  name="lessonTitle" type="text" class="form-control" id="lessonTitle" placeholder="Enter Lesson Title"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Description</label>
                                <textarea class="form-control" name="description">${lesson.description}</textarea>
                            </div>

                            <div class="form-group"> 
                                <button type="submit" class="btn btn-success">Update</button>
                                <button type="reset" class="btn btn-default">Reset</button>
                                <a class="btn btn-warning pull-right" href="${pageContext.request.contextPath}/admin/list_lesson">Back to list</a>
                            </div>
                        </form>
                    </div>
                    <div class="panel-footer"></div>
                </div>
            </div>

        </div>










    </div>
</div>
<script type="text/javascript">
    document.getElementById("courseId").value = ${lesson.course.courseId}
</script>
<%@include file="footer.jsp" %>