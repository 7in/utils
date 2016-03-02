<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>首页-规则中心</title>

    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

    <%--标签栏显示图标--%>
    <link rel="icon" href="/resources/img/logo.png" type="image/x-icon">

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="/resources/plugins/ace/assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/resources/plugins/ace/assets/css/font-awesome.min.css" />

    <!-- page specific plugin styles -->

    <!-- text fonts -->
    <link rel="stylesheet" href="/resources/plugins/ace/assets/css/ace-fonts.css" />

    <!-- ace styles -->
    <link rel="stylesheet" href="/resources/plugins/ace/assets/css/ace.min.css" id="main-ace-style" />

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="/resources/plugins/ace/assets/css/ace-part2.min.css" />
    <![endif]-->
    <link rel="stylesheet" href="/resources/plugins/ace/assets/css/ace-skins.min.css" />
    <link rel="stylesheet" href="/resources/plugins/ace/assets/css/ace-rtl.min.css" />

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="/resources/plugins/ace/assets/css/ace-ie.min.css" />
    <![endif]-->

    <%--项目css--%>
    <link rel="stylesheet" href="/resources/styles/plugins/font-awesome.min.css" />

    <!-- inline styles related to this page -->

    <!-- ace settings handler -->
    <script src="/resources/plugins/ace/assets/js/ace-extra.min.js"></script>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src="/resources/plugins/ace/assets/js/html5shiv.min.js"></script>
    <script src="/resources/plugins/ace/assets/js/respond.min.js"></script>
    <![endif]-->
</head>

<body class="no-skin">
<!-- #section:basics/navbar.layout -->
<div id="navbar" class="navbar navbar-default navbar-fixed-top">
    <script type="text/javascript">
        //				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
    </script>

    <div class="navbar-container" id="navbar-container">
        <!-- 缩小窗口后显示切换（显示、隐藏）菜单功能；#section:basics/sidebar.mobile.toggle-->
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler">
            <span class="sr-only">Toggle sidebar</span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>
        </button>

        <!-- /section:basics/sidebar.mobile.toggle -->
        <div class="navbar-header pull-left">
            <!-- #section:basics/navbar.layout.brand -->
            <a href="#" class="navbar-brand">
                <small>
                    <i class="icon icon-cogs"></i>
                    规则配置中心
                </small>
            </a>
        </div>

        <!-- #section:basics/navbar.dropdown -->
        <div class="navbar-buttons navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li class="grey">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="ace-icon fa fa-tasks"></i>
                        <span class="badge badge-grey">2</span>
                    </a>

                    <ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
                        <li class="dropdown-header">
                            <i class="ace-icon fa fa-check"></i>
                            执行进度（2）
                        </li>

                        <li>
                            <a href="#">
                                <div class="clearfix">
                                    <span class="pull-left">预约配送</span>
                                    <span class="pull-right">65%</span>
                                </div>

                                <div class="progress progress-mini">
                                    <div style="width:65%" class="progress-bar"></div>
                                </div>
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <div class="clearfix">
                                    <span class="pull-left">自动流转</span>
                                    <span class="pull-right">90%</span>
                                </div>

                                <div class="progress progress-mini progress-striped active">
                                    <div style="width:90%" class="progress-bar progress-bar-success"></div>
                                </div>
                            </a>
                        </li>

                        <li class="dropdown-footer">
                            <a href="#">
                                查看任务详情
                                <i class="ace-icon fa fa-arrow-right"></i>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="purple">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="ace-icon fa fa-bell icon-animated-bell"></i>
                        <span class="badge badge-important">1</span>
                    </a>

                    <ul class="dropdown-menu-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
                        <li class="dropdown-header">
                            <i class="ace-icon fa fa-exclamation-triangle"></i>
                            1 通知
                        </li>

                        <li>
                            <a href="#">
                                <div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-pink fa fa-comment"></i>
												错误提醒
											</span>
                                    <span class="pull-right badge badge-info">+1</span>
                                </div>
                            </a>
                        </li>

                        <li class="dropdown-footer">
                            <a href="#">
                                查看所有通知
                                <i class="ace-icon fa fa-arrow-right"></i>
                            </a>
                        </li>
                    </ul>
                </li>

                <!-- #section:basics/navbar.user_menu -->
                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo" src="/resources/plugins/ace/assets/avatars/user.jpg" alt="冲跃" />
								<span class="user-info">
									<small>Welcome,</small>
									冲跃
								</span>

                        <i class="ace-icon fa fa-caret-down"></i>
                    </a>

                    <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="#">
                                <i class="ace-icon fa fa-cog"></i>
                                设置
                            </a>
                        </li>

                        <li>
                            <a href="">
                                <i class="ace-icon fa fa-user"></i>
                                资料
                            </a>
                        </li>

                        <li class="divider"></li>

                        <li>
                            <a href="#">
                                <i class="ace-icon fa fa-power-off"></i>
                                登出
                            </a>
                        </li>
                    </ul>
                </li>

                <!-- /section:basics/navbar.user_menu -->
            </ul>
        </div>

        <!-- /section:basics/navbar.dropdown -->
    </div><!-- /.navbar-container -->
</div>

<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try{ace.settings.check('main-container' , 'fixed')}catch(e){}
    </script>

    <!-- #section:basics/sidebar -->
    <div id="sidebar" class="sidebar responsive sidebar-fixed">
        <script type="text/javascript">
            try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
        </script>

        <div class="sidebar-shortcuts" id="sidebar-shortcuts">
            <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
                <button class="btn btn-success">
                    <i class="ace-icon fa fa-signal" title="导航"></i>
                </button>

                <button class="btn btn-info" title="自定义视图">
                    <i class="ace-icon fa fa-pencil"></i>
                </button>
                <!-- #section:basics/sidebar.layout.shortcuts -->
                <button class="btn btn-warning" title="用户信息">
                    <i class="ace-icon fa fa-users"></i>
                </button>

                <button class="btn btn-danger" title="设置">
                    <i class="ace-icon fa fa-cog"></i>
                </button>
                <!-- /section:basics/sidebar.layout.shortcuts -->
            </div>

            <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
                <span class="btn btn-success" title="导航"></span>

                <span class="btn btn-info" title="自定义视图"></span>

                <span class="btn btn-warning" title="用户信息"></span>

                <span class="btn btn-danger" title="设置"></span>
            </div>
        </div><!-- /.sidebar-shortcuts -->
        <%--树形菜单--%>
        <ul class="nav nav-list">
            <li class="">
                <a href="">
                    <i class="menu-icon fa fa-desktop"></i>
                    <span class="menu-text"> 广场总览 </span>
                </a>

                <b class="arrow"></b>
            </li>

            <li class="">
                <a href="#" class="dropdown-toggle">
                    <i class="menu-icon fa fa-database"></i>
                    <span class="menu-text"> 业务线 </span>

                    <b class="arrow fa fa-angle-down"></b>
                </a>

                <b class="arrow"></b>

                <ul class="submenu">
                    <li class="">
                        <a href="#" class="dropdown-toggle">
                            <i class="menu-icon fa fa-caret-right"></i>
                            管理
                            <b class="arrow"></b>
                        </a>
                    </li>

                    <li class="">
                        <a href="#">
                            <i class="menu-icon fa fa-caret-right"></i>
                            创建
                        </a>

                        <b class="arrow"></b>
                    </li>

                    <li class="">
                        <a href="">
                            <i class="menu-icon fa fa-caret-right"></i>
                            修改
                        </a>

                        <b class="arrow"></b>
                    </li>

                    <li class="">
                        <a href="#">
                            <i class="menu-icon fa fa-caret-right"></i>
                            策略执行器
                        </a>

                        <b class="arrow"></b>
                    </li>
                </ul>
            </li>

            <li class="">
                <a href="#" class="dropdown-toggle">
                    <i class="menu-icon fa fa-list"></i>
                    <span class="menu-text"> 规则区 </span>

                    <b class="arrow fa fa-angle-down"></b>
                </a>

                <b class="arrow"></b>

                <ul class="submenu">
                    <li class="">
                        <a href="">
                            <i class="menu-icon fa fa-caret-right"></i>
                            管理规则
                        </a>

                        <b class="arrow"></b>
                    </li>

                    <li class="">
                        <a href="">
                            <i class="menu-icon fa fa-caret-right"></i>
                            创建规则
                        </a>

                        <b class="arrow"></b>
                    </li>

                    <li class="">
                        <a href="">
                            <i class="menu-icon fa fa-caret-right"></i>
                            新建规则
                        </a>

                        <b class="arrow"></b>
                    </li>

                    <li class="">
                        <a href=""  class="dropdown-toggle">
                            <i class="menu-icon fa fa-caret-right"></i>
                            规则链管理
                            <b class="arrow fa fa-angle-down"></b>
                        </a>

                        <b class="arrow"></b>

                        <ul class="submenu">
                            <li class="">
                                <a href="top-menu.html">
                                    <i class="menu-icon fa fa-caret-right"></i>
                                    管理规则
                                </a>

                                <b class="arrow"></b>
                            </li>

                            <li class="">
                                <a href="mobile-menu-1.html">
                                    <i class="menu-icon fa fa-caret-right"></i>
                                    创建
                                </a>

                                <b class="arrow"></b>
                            </li>

                            <li class="">
                                <a href="mobile-menu-2.html">
                                    <i class="menu-icon fa fa-caret-right"></i>
                                    修改
                                </a>

                                <b class="arrow"></b>
                            </li>
                        </ul>
                    </li>
                </ul>
            </li>

            <li class="">
                <a href="#" class="dropdown-toggle">
                    <i class="menu-icon fa fa-pencil-square-o"></i>
                    <span class="menu-text"> 场景划分 </span>

                    <b class="arrow fa fa-angle-down"></b>
                </a>

                <b class="arrow"></b>

                <ul class="submenu">
                    <li class="">
                        <a href="">
                            <i class="menu-icon fa fa-caret-right"></i>
                            行业维度
                        </a>

                        <b class="arrow"></b>
                    </li>

                    <li class="">
                        <a href="">
                            <i class="menu-icon fa fa-caret-right"></i>
                            场景维度
                        </a>

                        <b class="arrow"></b>
                    </li>
                </ul>
            </li>

            <li class="">
                <a href=""  class="dropdown-toggle">
                    <i class="menu-icon fa fa-list-alt"></i>
                    <span class="menu-text"> 源数据获取脚本 </span>
                    <b class="arrow fa fa-angle-down"></b>
                </a>

                <b class="arrow"></b>

                <ul class="submenu">
                    <li class="">
                        <a href="">
                            <i class="menu-icon fa fa-caret-right"></i>
                            脚本管理
                        </a>

                        <b class="arrow"></b>
                    </li>
                    <li class="">
                        <a href="">
                            <i class="menu-icon fa fa-caret-right"></i>
                            脚本创建
                        </a>

                        <b class="arrow"></b>
                    </li>
                    <li class="">
                        <a href="">
                            <i class="menu-icon fa fa-caret-right"></i>
                            脚本修改
                        </a>

                        <b class="arrow"></b>
                    </li>
                </ul>
            </li>


            <li class="active open">
                <a href="#" class="dropdown-toggle">
                    <i class="menu-icon fa fa-file-o"></i>

							<span class="menu-text">
								其他页面
								<span class="badge badge-primary">1</span>
							</span>

                    <b class="arrow fa fa-angle-down"></b>
                </a>

                <b class="arrow"></b>

                <ul class="submenu">
                    <li class="">
                        <a href="faq.html">
                            <i class="menu-icon fa fa-caret-right"></i>
                            FAQ
                        </a>

                        <b class="arrow"></b>
                    </li>
                </ul>
            </li>
        </ul><!-- /.nav-list -->

        <!-- #section:basics/sidebar.layout.minimize -->
        <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
            <i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
        </div>

        <!-- /section:basics/sidebar.layout.minimize -->
        <script type="text/javascript">
            try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
        </script>
    </div>

    <!-- /section:basics/sidebar -->
    <div class="main-content">
        <!-- #section:basics/content.breadcrumbs -->
        <div class="breadcrumbs breadcrumbs-fixed" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">首页</a>
                </li>

                <li>
                    <a href="#">场景概览</a>
                </li>
                <li class="active">概览</li>
            </ul><!-- /.breadcrumb -->

            <!-- #section:basics/content.searchbox -->
            <div class="nav-search" id="nav-search">
                <form class="form-search">
							<span class="input-icon">
								<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
								<i class="ace-icon fa fa-search nav-search-icon"></i>
							</span>
                </form>
            </div><!-- /.nav-search -->

            <!-- /section:basics/content.searchbox -->
        </div>

        <!-- /section:basics/content.breadcrumbs -->
        <div class="page-content">
            <!-- /section:settings.box -->
            <div class="page-content-area">
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->

                        <!-- PAGE CONTENT ENDS -->
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.page-content-area -->
        </div><!-- /.page-content -->
    </div><!-- /.main-content -->

    <div class="footer">
        <div class="footer-inner">
            <!-- #section:basics/footer -->
            <div class="footer-content">
						<span class="bigger-120">
							<span class="blue">菜鸟技术质量发展&nbsp;&nbsp;</span>
							规则中心 &copy; 2015-2016
						</span>
            </div>
            <!-- /section:basics/footer -->
        </div>
    </div>

    <%--回到顶部--%>
    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div><!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='/resources/plugins/ace/assets/js/jquery.min.js'>"+"<"+"/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='/resources/plugins/ace/assets/js/jquery1x.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
<script type="text/javascript">
    if('ontouchstart' in document.documentElement) document.write("<script src='/resources/plugins/ace/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="/resources/plugins/ace/assets/js/bootstrap.min.js"></script>

<!-- page specific plugin scripts -->

<!-- ace scripts -->
<script src="/resources/plugins/ace/assets/js/ace-elements.min.js"></script>
<script src="/resources/plugins/ace/assets/js/ace.min.js"></script>

<!-- inline scripts related to this page -->

<!-- the following scripts are used in demo only for onpage help and you don't need them -->
<link rel="stylesheet" href="/resources/plugins/ace/assets/css/ace.onpage-help.css" />
<link rel="stylesheet" href="/resources/plugins/ace/docs/assets/js/themes/sunburst.css" />

<script type="text/javascript"> ace.vars['base'] = '..'; </script>
<script src="/resources/plugins/ace/assets/js/ace/elements.onpage-help.js"></script>
<script src="/resources/plugins/ace/assets/js/ace/ace.onpage-help.js"></script>
<script src="/resources/plugins/ace/docs/assets/js/rainbow.js"></script>
<script src="/resources/plugins/ace/docs/assets/js/language/generic.js"></script>
<script src="/resources/plugins/ace/docs/assets/js/language/html.js"></script>
<script src="/resources/plugins/ace/docs/assets/js/language/css.js"></script>
<script src="/resources/plugins/ace/docs/assets/js/language/javascript.js"></script>
</body>
</html>
