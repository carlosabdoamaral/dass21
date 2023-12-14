package com.carlosamaral.dass21.service;

import com.carlosamaral.dass21.enums.ScaleEnum;
import com.carlosamaral.dass21.enums.SymptomTypeEnum;
import org.springframework.stereotype.Service;

@Service
public class SintomaService {
    private boolean between(int variable, int minValueInclusive, int maxValueInclusive) {
        return variable >= minValueInclusive && variable <= maxValueInclusive;
    }
    private ScaleEnum ansiedadeCalc(Integer pontuacao) {
        if (between(pontuacao, 0, 3))
            return ScaleEnum.SEM_SINTOMA;
        else if (between(pontuacao, 4, 5))
            return ScaleEnum.LEVE;
        else if (between(pontuacao, 6,7))
            return ScaleEnum.MODERADO;
        else if (between(pontuacao, 8, 9))
            return ScaleEnum.GRAVE;
        else
            return ScaleEnum.MUITO_GRAVE;
    }

    private ScaleEnum depressaoCalc(Integer pontuacao) {
        if (between(pontuacao, 0, 4))
            return ScaleEnum.SEM_SINTOMA;
        else if (between(pontuacao, 5, 6))
            return ScaleEnum.LEVE;
        else if (between(pontuacao, 7,10))
            return ScaleEnum.MODERADO;
        else if (between(pontuacao, 11, 13))
            return ScaleEnum.GRAVE;
        else
            return ScaleEnum.MUITO_GRAVE;
    }

    private ScaleEnum estresseCalc(Integer pontuacao) {
        if (between(pontuacao, 0, 7))
            return ScaleEnum.SEM_SINTOMA;
        else if (between(pontuacao, 8, 9))
            return ScaleEnum.LEVE;
        else if (between(pontuacao, 10,12))
            return ScaleEnum.MODERADO;
        else if (between(pontuacao, 13, 16))
            return ScaleEnum.GRAVE;
        else
            return ScaleEnum.MUITO_GRAVE;
    }

    public ScaleEnum getScaleByType(SymptomTypeEnum type, Integer score) {
        return switch (type) {
            case SymptomTypeEnum.DEPRESSAO -> this.depressaoCalc(score);
            case SymptomTypeEnum.ANSIEDADE -> this.ansiedadeCalc(score);
            case SymptomTypeEnum.ESTRESSE -> this.estresseCalc(score);
        };
    }
}
