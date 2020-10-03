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
                                <h4>Upload Course Book</h4>
                            </div>
                            <div class="panel-body">

                                <form method="POST" action="<%= request.getContextPath()%>/admin/fileUpload" enctype="multipart/form-data">

                                    <input type="hidden" name="courseId" value="${course.courseId}" class="form-control"/>

                                    <!--<input type="file" name="file">-->

                                    <div class="input-group" style="margin-top: 10px;">
                                        <label class="input-group-btn">
                                            <span class="btn btn-primary">
                                                Browse&hellip; <input accept=".pdf" type="file" name="file" style="display: none;" multiple>
                                            </span>
                                        </label>
                                        <input type="text" class="form-control" readonly>
                                    </div>
                                    
                                    <button type="submit" class="btn btn-success" style="margin-top: 10px;"><i class="fa fa-floppy-o"></i> Update</button>
                                    <a style="margin-top: 10px;" class="btn btn-warning pull-right" href="${pageContext.request.contextPath}/admin/list_course"><i class="fa fa-backward"></i> Back to List</a>
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