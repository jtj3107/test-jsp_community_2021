package com.jhs.exam.exam2.container;

import java.util.ArrayList;
import java.util.List;

import com.jhs.exam.exam2.app.App;
import com.jhs.exam.exam2.http.controller.AdmHomeController;
import com.jhs.exam.exam2.http.controller.UsrArticleController;
import com.jhs.exam.exam2.http.controller.UsrHomeController;
import com.jhs.exam.exam2.http.controller.UsrLikeController;
import com.jhs.exam.exam2.http.controller.UsrMemberController;
import com.jhs.exam.exam2.interceptor.BeforeActionInterceptor;
import com.jhs.exam.exam2.interceptor.NeedAdminInterceptor;
import com.jhs.exam.exam2.interceptor.NeedLoginInterceptor;
import com.jhs.exam.exam2.interceptor.NeedLogoutInterceptor;
import com.jhs.exam.exam2.repository.ArticleRepository;
import com.jhs.exam.exam2.repository.BoardRepository;
import com.jhs.exam.exam2.repository.MemberRepository;
import com.jhs.exam.exam2.service.ArticleService;
import com.jhs.exam.exam2.service.BoardService;
import com.jhs.exam.exam2.service.EmailService;
import com.jhs.exam.exam2.service.MemberService;

// 웹사이트 실행이 모든 객체 한번 실행(필요시 마다 따로 만들어 따로 실행하는것이 아닌 한번에 실행하여 사용)
public class Container {
	private static List<ContainerComponent> containerComponents;

	public static App app;
	
	public static BeforeActionInterceptor beforeActionInterceptor;
	public static NeedLoginInterceptor needLoginInterceptor;
	public static NeedLogoutInterceptor needLogoutInterceptor;
	public static NeedAdminInterceptor needAdminInterceptor;

	public static ArticleRepository articleRepository;
	public static ArticleService articleService;
	public static UsrArticleController usrArticleController;
	public static UsrLikeController usrLikeController;

	public static MemberRepository memberRepository;
	public static MemberService memberService;
	public static UsrMemberController usrMemberController;

	public static UsrHomeController usrHomeController;

	public static BoardRepository boardRepository;
	public static BoardService boardService;
	public static EmailService emailService;

	public static AdmHomeController admHomeController;

	public static void init() {
		containerComponents = new ArrayList<>();

		// 의존성 세팅 시작
		app = addContainerComponent(new App());
		memberRepository = addContainerComponent(new MemberRepository());
		boardRepository = addContainerComponent(new BoardRepository());
		articleRepository = addContainerComponent(new ArticleRepository());

		memberService = addContainerComponent(new MemberService());
		boardService = addContainerComponent(new BoardService());
		articleService = addContainerComponent(new ArticleService());

		beforeActionInterceptor = addContainerComponent(new BeforeActionInterceptor());
		needLoginInterceptor = addContainerComponent(new NeedLoginInterceptor());
		needLogoutInterceptor = addContainerComponent(new NeedLogoutInterceptor());
		needAdminInterceptor = addContainerComponent(new NeedAdminInterceptor());

		usrMemberController = addContainerComponent(new UsrMemberController());
		usrArticleController = addContainerComponent(new UsrArticleController());
		usrHomeController = addContainerComponent(new UsrHomeController());
		usrLikeController = addContainerComponent(new UsrLikeController());
		emailService = addContainerComponent(new EmailService());

		admHomeController = addContainerComponent(new AdmHomeController());

		// 객체 초기화
		for (ContainerComponent containerComponent : containerComponents) {
			containerComponent.init();
		}
	}

	private static <T> T addContainerComponent(ContainerComponent containerComponent) {
		containerComponents.add(containerComponent);

		return (T) containerComponent;
	}
}
