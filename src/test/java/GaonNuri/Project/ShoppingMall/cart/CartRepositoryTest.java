package GaonNuri.Project.ShoppingMall.cart;

//@SpringBootTest
//public class CartRepositoryTest {
//
//    @Autowired
//    CartRepository cartRepository;
//
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @PersistenceContext
//    EntityManager em;

//    @Test
//    public void 장바구니_회원_매핑테스트(){
//
//        Member member = Member.builder()
//                .email("ctce7226@gmail.com")
//                .password("lee~2371765")
//                .name("이진")
//                .build();
//        this.memberRepository.save(member);
//
//        Cart cart = new Cart();
//        cart.setMember(member);
//        cartRepository.save(cart);
//
//        Cart savedcart = cartRepository.findById(cart.getId())
//                .orElseThrow(EntityNotFoundException::new);
//        Assertions.assertEquals(savedcart.getMember().getId(), member.getId());
//    }

//}
