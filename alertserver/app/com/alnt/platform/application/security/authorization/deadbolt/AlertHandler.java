package com.alnt.platform.application.security.authorization.deadbolt;

//public class AlertHandler{
//	
//}
//@HandlerQualifiers.MainHandler
//public class AlertHandler extends AbstractDeadboltHandler
//{
//    private static final Logger LOGGER = LoggerFactory.getLogger(AlertHandler.class);
//
//    private final DynamicResourceHandler dynamicHandler;
//    private final UserDao userDao;
//
//    @Inject
//    public AlertHandler(final UserDao userDao)
//    {
//        super();
//        Map<String, DynamicResourceHandler> delegates = new HashMap<>();
//        delegates.put("niceName",
//                      new NiceNameDynamicResourceHandler());
//        this.dynamicHandler = new CompositeDynamicResourceHandler(delegates);
//        this.userDao = userDao;
//    }
//
//    @Override
//    public CompletionStage<Optional<? extends Subject>> getSubject(final Http.RequestHeader requestHeader)
//    {
//        final Optional<Http.Cookie> maybeUserCookie = Optional.ofNullable(requestHeader.cookie("user"));
//        return CompletableFuture.supplyAsync(() -> maybeUserCookie.flatMap(cookie -> userDao.getByUserName(cookie.value())));
//    }
//
//    @Override
//    public CompletionStage<Optional<Result>> beforeAuthCheck(final Http.RequestHeader requestHeader, final Optional<String> content)
//    {
//        return CompletableFuture.completedFuture(Optional.empty());
//    }
//
//    @Override
//    public CompletionStage<Optional<DynamicResourceHandler>> getDynamicResourceHandler(final Http.RequestHeader requestHeader)
//    {
//        return CompletableFuture.supplyAsync(() -> Optional.of(dynamicHandler));
//    }
//
//    @Override
//    public CompletionStage<List<? extends Permission>> getPermissionsForRole(final String roleName) {
//        return CompletableFuture.completedFuture(Collections.singletonList(new SecurityPermission("killer.undead.*")));
//    }
//
//    @Override
//    public String handlerName()
//    {
//        return Constants.DEFAULT_HANDLER_KEY;
//    }
//
//    @Override
//    public void onAuthSuccess(Http.RequestHeader requestHeader,
//                              String constraintType,
//                              ConstraintPoint constraintPoint)
//    {
//        LOGGER.info("[{} - {}] - authorization succeeded for [{}]",
//                    constraintPoint,
//                    constraintType,
//                    requestHeader.attrs());
//    }
//}