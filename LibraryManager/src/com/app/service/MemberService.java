import java.util.List;

import com.app.exception.ServiceException;
import com.app.model.Member;

public interface MemberService {

	public List<Member> getList() throws ServiceException;
	public List<Member> getListMemberEmpruntPossible() throws ServiceException;
	public Member getById(int id) throws ServiceException;
	public int create(String nom, String prenom, String adresse, String email, String telephone) throws ServiceException;
	public void update(Member member) throws ServiceException;
	public void delete(int id) throws ServiceException;
	public int count() throws ServiceException;

}
