/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.daos.ImpressionDao;
import cz.muni.fi.pa165.library.dtos.ImpressionTO;
import cz.muni.fi.pa165.library.entities.Impression;
import cz.muni.fi.pa165.library.utils.Convertor;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Matej
 */
@Service
@Transactional
public class ImpressionServiceImpl implements ImpressionService {

    @Autowired
    private ImpressionDao impressionDao;

    public void createImpression(ImpressionTO impressionTO) {
        Impression impressionEntity = Convertor.convert(impressionTO);
        impressionDao.createImpression(impressionEntity);
        impressionTO.setId(impressionEntity.getId());
    }

    public List<ImpressionTO> findAllImpressions() {
        List<ImpressionTO> impressionTOs = new ArrayList<ImpressionTO>();
        List<Impression> impressionEntities = impressionDao.findAllImpressions();
        for (Impression impression : impressionEntities) {
            impressionTOs.add(Convertor.convert(impression));
        }
        return impressionTOs;
    }

    public ImpressionTO findImpressionById(Long id) {
        return Convertor.convert(impressionDao.findImpressionById(id));
    }

    public void deleteImpression(ImpressionTO impressionTO) {
        impressionDao.deleteImpression(Convertor.convert(impressionTO));
        impressionTO.setId(null);
    }

    public void updateImpression(ImpressionTO impressionTO) {
        impressionDao.updateImpression(Convertor.convert(impressionTO));
    }

}
