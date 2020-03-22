import java.time.LocalDate;
import java.util.List;

import com.app.exception.ServiceException;
import com.app.model.*;

public interface LendingService {
	public List<Lending> getList() throws ServiceException;
	public List<Lending> getListCurrent() throws ServiceException;
	public List<Lending> getListCurrentByMember(int idMember) throws ServiceException;
	public List<Lending> getListCurrentByBook(int idBook) throws ServiceException;
	public Lending getById(int id) throws ServiceException;
	public void create(int idMember, int idBook, LocalDate dateLending) throws ServiceException;
	public void returnBook(int id) throws ServiceException;
	public int count() throws ServiceException;
	public boolean isBookDispo(int idBook) throws ServiceException;
	public boolean isLendingPossible(Member Member) throws ServiceException;
}
