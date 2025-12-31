package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.InitiativeDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class InitiativeModelHibImp implements InitiativeModelInt {
	@Override
	public long add(InitiativeDTO dto) throws ApplicationException, DuplicateRecordException {

		Session session = null;
		Transaction tx = null;

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.save(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in Initiative Add " + e.getMessage());
		} /*
			 * finally { session.close(); }
			 */
		return dto.getId();
	}

	@Override
	public void delete(InitiativeDTO dto) throws ApplicationException {
		Session session = null;
		Transaction tx = null;

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.delete(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in Initiative Delete " + e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public void update(InitiativeDTO dto) throws ApplicationException, DuplicateRecordException {

		Session session = null;
		Transaction tx = null;

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in Initiative Update " + e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public InitiativeDTO findByPK(long pk) throws ApplicationException {
		Session session = null;
		InitiativeDTO dto = null;

		try {
			session = HibDataSource.getSession();
			dto = (InitiativeDTO) session.get(InitiativeDTO.class, pk);
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Initiative FindByPK");
		} finally {
			session.close();
		}
		return dto;
	}

	@Override
	public List list() throws ApplicationException {
		return list(0, 0);
	}

	@Override
	public List list(int pageNo, int pageSize) throws ApplicationException {
		Session session = null;
		List list = null;

		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(InitiativeDTO.class);

			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Initiative List");
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List search(InitiativeDTO dto) throws ApplicationException {
		return search(dto, 0, 0);
	}

	@Override
	public List search(InitiativeDTO dto, int pageNo, int pageSize) throws ApplicationException {

		Session session = null;
		ArrayList<InitiativeDTO> list = null;

		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(InitiativeDTO.class);

			if (dto != null) {
				if (dto.getId() != null) {
					criteria.add(Restrictions.eq("id", dto.getId()));
				}
				if (dto.getInitiativeName() != null && dto.getInitiativeName().length() > 0) {
					criteria.add(Restrictions.like("initiativeName", dto.getInitiativeName() + "%"));
				}
				if (dto.getType() != null && dto.getType().length() > 0) {
					criteria.add(Restrictions.like("type", dto.getType() + "%"));
				}
				if (dto.getVersion() > 0) {
					criteria.add(Restrictions.eq("version", dto.getVersion()));
				}
			}

			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}

			list = (ArrayList<InitiativeDTO>) criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Initiative Search");
		} finally {
			session.close();
		}
		return list;
	}
}
