/**
 * Copyright (c) 2017 ytk-learn https://github.com/yuantiku
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.fenbi.ytklearn.loss;

import java.util.Map;

/**
 * The interface {@code ILossFunction} provides interfaces:
 *   <ul>
 *       <li>{@link #loss(double[], double[])} for calculating objective loss function</li>
 *       <li>{@link #predict(double)} for predicting</li>
 *       <li>{@link #firstDerivative(double, double)} for calculating first derivative of score function for numeric optimization</li>
 *       <li>{@link #secondDerivative(double, double)} for calculating second derivative of score function for numeric optimization </li>
 *   </ul>
 * <p> The same {@code loss function} can have different score function(or different model,
 *     e.g. linear model, factorization machine model, ...
 *
 * <p> The class which extends {@code ILossFunction} is used for {@link com.fenbi.ytklearn.optimizer.HoagOptimizer} and
 *     {@link com.fenbi.ytklearn.predictor.OnlinePredictor}
 *
 * <p> If you want to a new loss function, firstly you must implement this interface,
 *     then add this loss function to {@link LossFunctions} factory
 *
 * @author xialong
 */
public abstract class AbstractLossFunction implements ILossFunction {

    /**
     * Transforms predict result back to score
     * @param pred Prediction
     * @return score value
     */
    public double pred2Score(double pred) {
        return pred;
    }

    /**
     * Calculates loss value provided with multi scores and labels.
     * <p> Usually used by multi-classification:
     * {@link SoftmaxFunction},
     * {@link MulticlassHingeFunction},
     * {@link MulticlassSmoothHingeFunction},
     * {@link MulticlassL2HingeFunction}
     *
     * @param score
     *        The score value of score function
     * @param label
     *        The label of sample
     * @return loss
     */
    public double loss(double[] score, double[] label) {
        return -1;
    }

    /**
     * Calculates prediction of multi-classification:
     *
     * {@link SoftmaxFunction},
     * {@link MulticlassHingeFunction},
     * {@link MulticlassSmoothHingeFunction},
     * {@link MulticlassL2HingeFunction}
     *
     * @param score
     *        The score value of score function
     * @param pred
     *        Output of prediction results
     */
    public void predict(double[] score, double[] pred) {
    }

    public void getDerivativeFast(double pred, double label, double[] deri) {
        deri[0] = firstDerivative(pred, label);
        deri[1] = secondDerivative(pred, label);
    }

    public void getDerivativeFast(double[] pred, double[] label, double[] firstDeri, double[] secondDeri) {
    }

    public void firstDerivative(double[] score, double[] label, double[] firstDeri) {
    }

    public void secondDerivative(double[] score, double[] label, double[] secondDeri) {
    }

    public double all(double[] score,
                              double[] label,
                              double[] predict,
                              double[] firstDeri,
                              double[] secondDeri
    ) {
        return -1;
    }

    public boolean checkLabel(float label) {
        return true;
    }

    public boolean checkLabel(float[] label) {
        return true;
    }

    public void setParam(Map<String, String> params) {
    }
}
