package SpringPractice.SpringPractice_Basic.Controller;

import SpringPractice.SpringPractice_Basic.domain.Member;
import SpringPractice.SpringPractice_Basic.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //회원등록받을 페이지 요청
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    //회원정보 입력 post요청
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();

        //post를 통해 받은 name을 이용하여 member객체 정보입력후 join
        member.setName(form.getName());
        memberService.join(member);

        //home 화면으로 리다이렉팅
        return "redirect:/";
    }

    //회원 조회 기능
    @GetMapping("/members")
    public String list(Model model){ // view에 사용자 리스트를 전달해주어야하므로 model사용
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
