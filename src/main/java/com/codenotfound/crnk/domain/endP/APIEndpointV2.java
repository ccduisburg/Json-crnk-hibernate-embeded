package com.codenotfound.crnk.domain.endP;

import io.crnk.core.engine.internal.utils.StringUtils;
import io.crnk.core.exception.ResourceNotFoundException;
import io.crnk.core.queryspec.FilterSpec;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepositoryV2;
import io.crnk.core.resource.list.DefaultResourceList;
import io.crnk.core.resource.list.ResourceList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by maurice on 5/4/17
 */
public abstract class APIEndpointV2<T, TT extends Serializable> implements ResourceRepositoryV2<T, TT> {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private Class<T> entityClass;

//    @Inject private CurrentUserImpl currentUser;

    @SuppressWarnings("unchecked")
    public APIEndpointV2() {
        entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public Class<T> getResourceClass() {
        return entityClass;
    }

    public T findByID(TT id) {
        return null;
    }

    @Override
    public final T findOne(TT id, QuerySpec querySpec) {
        T t;
        try {
            t = findByID(id);
        } catch (GlobalError e) {
            throw e;
        } catch (NoResultException e) {
            throw new ResourceNotFoundException(entityClass.getSimpleName());
        } catch (Exception e) {
            getLogger().error("ERROR", e);
            throw new GlobalError("100", e);
        }
        if (t == null) {
            throw new ResourceNotFoundException(entityClass.getSimpleName());
        }
        return t;
    }

    public List<T> findBy(QuerySpec querySpec) {
        return null;
    }

    @Override
    public final ResourceList<T> findAll(QuerySpec querySpec) {
        if (querySpec != null) {
            if (querySpec.getLimit() == null) {
                querySpec.setLimit(25L);
            }
        }
        List<T> ret;
        try {
            ret = findBy(querySpec);
        } catch (GlobalError e) {
            throw e;
        } catch (NoResultException e) {
            throw new ResourceNotFoundException(entityClass.getSimpleName());
        } catch (Exception e) {
            getLogger().error("ERROR", e);
            throw new GlobalError("100", e);
        }
        if (ret instanceof ResourceList) {
            return (ResourceList<T>) ret;
        }
        if (ret == null) {
            ret = Collections.emptyList();
        }
        return new DefaultResourceList<>(ret, null, null);
    }

    @Override
    public final ResourceList<T> findAll(Iterable<TT> iterable, QuerySpec querySpec) {
        List<T> list = new ArrayList<>();
        for (TT tt : iterable) {
            T t = findByID(tt);
            if (t != null) {
                list.add(t);
            }
        }
        return new DefaultResourceList<>(list, null, null);
    }

    @SuppressWarnings("unchecked")
    public <O> Optional<O> getFilterParam(QuerySpec querySpec, String field) {
        try {
            return (Optional<O>) querySpec.getFilters().stream()
                    .filter(d -> !d.getAttributePath().isEmpty()
                            && d.getAttributePath().get(0).equals(field))
                    .map(FilterSpec::getValue).findFirst();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @SuppressWarnings("unchecked")
    public <O> Optional<O> getFilterParam(QuerySpec querySpec, String field, Set<String> operators) {
        try {
            return (Optional<O>) querySpec.getFilters().stream()
                    .filter(d -> !d.getAttributePath().isEmpty()
                            && d.getAttributePath().get(0).equals(field)
                            && operators.contains(d.getOperator().getName()))
                    .map(FilterSpec::getValue).findFirst();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @SuppressWarnings("unchecked")
    public <O> Set<O> getFilterParamSet(QuerySpec querySpec, String field) {
        try {
            return (Set<O>) querySpec.getFilters().stream()
                    .filter(d -> !d.getAttributePath().isEmpty()
                            && d.getAttributePath().get(0).equals(field))
                    .map(FilterSpec::getValue)
                    .flatMap(f -> ((Set) f).stream())
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            return getFilterParamSetAlternative(querySpec, field);
        }
    }

    @SuppressWarnings("unchecked")
    private <O> Set<O> getFilterParamSetAlternative(QuerySpec querySpec, String field) {
        try {
            return (Set<O>) querySpec.getFilters().stream()
                    .filter(d -> !d.getAttributePath().isEmpty()
                            && d.getAttributePath().get(0).equals(field))
                    .map(FilterSpec::getValue)
                    .collect(Collectors.toSet());
        } catch (Exception e1) {
            return Collections.emptySet();
        }
    }

    @SuppressWarnings("unchecked")
    public <O> Set<O> getNestedSpecsSet(QuerySpec querySpec, Class c, String field) {
        Collection<QuerySpec> nestedSpecs = querySpec.getNestedSpecs();
        Set<O> set = new HashSet<>();
        for (QuerySpec qs : nestedSpecs) {
            if (qs.getResourceClass().equals(c)) {
                for (FilterSpec fs : qs.getFilters()) {
                    if (StringUtils.join(".", fs.getAttributePath()).equals(field)) {
                        if (fs.getValue() instanceof Set) {
                            return fs.getValue();
                        } else {
                            set.add(fs.getValue());
                        }
                    }
                }
            }
        }
        return set;
    }

    @Override
    public void delete(TT tt) {
    }

    @Override
    public <S extends T> S create(S s) {
        return save(s);
    }

    @Override
    public <S extends T> S save(S s) {
        return s;
    }

//    public CurrentUserImpl getUser() {
//        return currentUser;
//    }

    public Logger getLogger() {
        return logger;
    }
}
