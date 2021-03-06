package com.jshop.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.jshop.dao.ProductTDao;
import com.jshop.entity.ProductT;

/**
 * A data access object (DAO) providing persistence and search support for
 * ProductT entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.jshop.entity.ProductT
 * @author MyEclipse Persistence Tools
 */
@Repository("productTDao")
public class ProductTDaoImpl extends HibernateDaoSupport implements ProductTDao {
	private static final Logger log = LoggerFactory.getLogger(ProductTDaoImpl.class);
	public int addProductT(ProductT pt) {
		log.debug("save ProductT");
		try {
			this.getHibernateTemplate().save(pt);
			log.debug("save successful");
			return 1;
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public int countfindAllProductT(String creatorid) {
		log.debug("countfindAllProductT");
		try {
			String queryString = "select count(*) from ProductT as pt where pt.creatorid=:creatorid";
			List list = this.getHibernateTemplate().findByNamedParam(queryString, "creatorid", creatorid);
			if (list.size() > 0) {
				Object o = list.get(0);
				long l = (Long) o;
				return (int) l;
			}
			return 0;
		} catch (RuntimeException re) {
			log.error("countfindAllProductT error", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ProductT> findAllProductT(final int currentPage, final int lineSize, final String creatorid) {
		log.debug("find all ProductT");
		try {
			List<ProductT> list = this.getHibernateTemplate().executeFind(new HibernateCallback() {

				String queryString = "from ProductT as pt where pt.creatorid=:creatorid order by productid desc";

				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					Query query = session.createQuery(queryString);
					query.setFirstResult((currentPage - 1) * lineSize);
					query.setMaxResults(lineSize);
					query.setParameter("creatorid", creatorid);
					List list = query.list();
					return list;
				}
			});
			if (list.size() > 0) {
				return list;
			}
			return null;
		} catch (RuntimeException re) {
			log.error("find all ProductT error", re);
			throw re;
		}
	}

	public List<ProductT> findAllProductTByGoodsid(String creatorid, String goodsid) {
		log.debug("findAllProductTByGoodsid");
		try {
			String queryString = "from ProductT as pt where pt.creatorid=:creatorid and pt.goodsid=:goodsid";
			List list = this.getHibernateTemplate().findByNamedParam(queryString, new String[] { "creatorid", "goodsid" }, new Object[] { creatorid, goodsid });
			if (list != null && list.size() > 0) {
				return list;
			}
			return null;
		} catch (RuntimeException re) {
			log.error("findAllProductTByGoodsid error", re);
			throw re;
		}
	}

	public int updateProductT(final ProductT pt) {
		log.debug("updateProductT");
		try {
			this.getHibernateTemplate().update(pt);
			return 1;
		} catch (RuntimeException re) {
			log.error("updateProductT error", re);
			throw re;
		}
	}

	public List<ProductT> findProductTByproductid(String creatorid, String productid) {
		log.debug("findAllProductTByGoodsid");
		try {
			String queryString = "from ProductT as pt where pt.creatorid=:creatorid and pt.productid=:productid";
			List list = this.getHibernateTemplate().findByNamedParam(queryString, new String[] { "creatorid", "productid" }, new Object[] { creatorid, productid });
			if (list != null && list.size() > 0) {
				return list;
			}
			return null;
		} catch (RuntimeException re) {
			log.error("findAllProductTByGoodsid error", re);
			throw re;
		}
	}

	public int delProductTBygoodsid(final String goodsid, final String creatorid) {
		log.debug("delProductTBygoodsid");
		try {

			final String queryString = "delete from ProductT as pt where pt.goodsid=:goodsid and pt.creatorid=:creatorid";
			this.getHibernateTemplate().execute(new HibernateCallback() {

				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					int i = 0;
					Query query = session.createQuery(queryString);
					query.setParameter("goodsid", goodsid);
					query.setParameter("creatorid", creatorid);
					i = query.executeUpdate();
					return i;
				}
			});
		} catch (RuntimeException re) {
			log.error("delProductTBygoodsid failed", re);
			throw re;
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	public List<ProductT> sortAllProductT(final int currentPage, final int lineSize, final String creatorid, final String queryString) {
		log.debug("find all ProductT");
		try {
			List<ProductT> list = this.getHibernateTemplate().executeFind(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException, SQLException {
					Query query = session.createQuery(queryString);
					query.setFirstResult((currentPage - 1) * lineSize);
					query.setMaxResults(lineSize);
					query.setParameter("creatorid", creatorid);
					List list = query.list();
					return list;
				}
			});
			if (list.size() > 0) {
				return list;
			}
			return null;
		} catch (RuntimeException re) {
			log.error("find all ProductT error", re);
			throw re;
		}
	}
	
	
	
	
}