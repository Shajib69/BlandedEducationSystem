<%@page import="java.util.Date"%>
<%@include file="header.jsp" %>
<script type="text/javascript">
    var qApp = angular.module("qApp", []);
    qApp.controller("qCtrl", function ($scope, $http) {

        //get All Courses
        $scope.courses = [];
        $scope.getAllCourse = function () {
            $http({
                method: 'GET',
                url: 'http://localhost:8080/BlendedEducationSystem/topics/allCoursesInfo'
            }).then(function (response) {
                $scope.courses = response.data;
            });
        };
        $scope.getAllCourse();

        $scope.clickedCourse = {};

        $scope.lessons = [];
        $scope.getAllLessonsByCourseId = function () {
//        alert($scope.clickedCourse.courseId);
            $http({
                method: 'GET',
                url: 'http://localhost:8080/BlendedEducationSystem/topics/lessonByCourseId/' + $scope.clickedCourse.courseId
            }).then(function (response) {
                $scope.lessons = response.data;
            });
            //alert($scope.lessons[0].lessonTitle);
        };

        $scope.clickedLesson = {};


    });
</script>
<div ng-app="qApp"  id="page-wrapper" style="min-height: 800px;">

    <div class="container-fluid" ng-controller="qCtrl">
        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">
                    <i class="fa fa-file"></i> Create Question
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
                        <h4>Create Question</h4>
                    </div>
                    <div class="panel-body">
                        <form action="${pageContext.request.contextPath}/admin/saveQuestion" method="post">
                            
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

                            <hr/>
                            <hr/>

                            <div class="form-group">
                                <label class="control-label">Question:</label>
                                <textarea name="question" class="form-control"></textarea>
                            </div>

                            Options: 
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="control-label">A.</label>
                                        <textarea name="aone" class="form-control"></textarea>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="control-label">B.</label>
                                        <textarea name="atwo" class="form-control"></textarea>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="control-label">C.</label>
                                        <textarea name="athree" class="form-control"></textarea>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label class="control-label">D.</label>
                                        <textarea name="afour" class="form-control"></textarea>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label">Correct Answer:</label>
                                <select name="canswer" class="form-control">
                                    <option value="select">--- Select Correct Answer ---</option>
                                    <option value="aone">A</option>
                                    <option value="atwo">B</option>
                                    <option value="athree">C</option>
                                    <option value="afour">D</option>
                                </select>
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