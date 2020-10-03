<%@page import="java.util.Date"%>
<%@include file="header.jsp" %>
<div ng-app="topicsApp"  id="page-wrapper" style="min-height: 800px;">
    <div class="container-fluid">
        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">
                    <i class="fa fa-file"></i> Topics
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
            <div class="col-md-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4>Create Topics</h4>
                    </div>
                    <div class="panel-body" ng-controller="topicsCtrl">
                        <form action="${pageContext.request.contextPath}/admin/saveTopics" method="post">
                            <div class="form-group">
                                <label class="control-label">Select Course:</label>
                                <select ng-change="getAllLessonsByCourseId()" class="form-control" ng-model="clickedCourse" ng-options="course.courseName for course in courses"></select>
                            </div>
                            <div class="form-group">
                                <label class="control-label"></label>
                                <input type="hidden"  name="courseId" value="{{clickedCourse.courseId}}"
                            </div>
                            <div class="form-group">
                                <label class="control-label">Select Lesson:</label>
                                <select name="lessonId" class="form-control">
                                    <option value="{{lesson.lessonId}}" ng-repeat="lesson in lessons">{{lesson.lessonTitle}}</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label class="control-label">Topics Title:</label>
                                <input  name="topicsTitle" type="text" class="form-control" id="lessonTitle" placeholder="Enter Topic Title"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Description</label>
                                <textarea class="form-control" name="description"></textarea>
                            </div>
                            <div class="form-group">
                                <label class="control-label">Topics Duration (minute):</label>
                                <input  name="duration" type="number" class="form-control"/>
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
        </div>



    </div>
</div>

<%@include file="footer.jsp" %>