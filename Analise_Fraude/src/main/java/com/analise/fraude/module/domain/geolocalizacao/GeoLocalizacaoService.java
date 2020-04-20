package com.analise.fraude.module.domain.geolocalizacao;

import com.analise.fraude.module.domain.solicitacaocadastro.SolicitacaoCadastroResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class GeoLocalizacaoService {

    public Double obtemDistanciaMediaEntreSolicitacoes(Double latSolicitacao,
                                                       Double longSolicitacao,
                                                       List<SolicitacaoCadastroResponse> solicitacoes){

        Double soma = 0.0;
        if(!CollectionUtils.isEmpty(solicitacoes)) {

            for (SolicitacaoCadastroResponse solicitacao : solicitacoes) {

                soma += distanciaGeografica(latSolicitacao, solicitacao.getLatitude(),
                        longSolicitacao, solicitacao.getLongitude());
            }
        }

        return (soma/solicitacoes.size());
    }

    private double distanciaGeografica(double lat1, double lat2,
                                double lon1,double lon2)
    {

        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));
        double r = 6371;
        return(c * r);
    }
}
