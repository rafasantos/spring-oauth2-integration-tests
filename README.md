# spring-oauth2-integration-tests
A sample project that shows how to write Spring integration tests for an application using OAuth2 from Spring Security.

This project is accompanied by [this article](http://wp.me/p5qBJ9-c6)




https://github.com/luminis-ams/spring-oauth2-integration-tests.git

https://sharing.luminis.eu/blog/integration-testing-a-spring-restful-web-service-secured-with-oauth2/



http://www.zakariaamine.com/2018-03-01/using-oauth2-in-spring-scopes
```
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			 .antMatchers(HttpMethod.GET,"/hello").access("#oauth2.hasScope('read')")
			 .antMatchers(HttpMethod.GET,"/foo").access("#oauth2.hasScope('read')")
			 .antMatchers(HttpMethod.POST,"/bar").access("#oauth2.hasScope('write')")
			 .antMatchers(HttpMethod.DELETE,"/test").access("#oauth2.hasScope('trust')")
			.anyRequest().authenticated().
			 and().csrf().disable();
	}
```
http://engineering.pivotal.io/post/faking_oauth_sso/
