/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.frameworks.connectors.properties;

import org.odpi.openmetadata.frameworks.connectors.ffdc.OCFErrorCode;
import org.odpi.openmetadata.frameworks.connectors.ffdc.OCFRuntimeException;

import java.util.Iterator;

/**
 * Licenses supports an iterator over a list of licenses for the asset.  Callers can use it to step through the list
 * just once.  If they want to parse the list again, they could use the copy/clone constructor to create
 * a new iterator.
 */
public abstract class AssetLicenses extends AssetPropertyIteratorBase implements Iterator<AssetLicense>
{
    /**
     * Typical Constructor creates an iterator with the supplied list of elements.
     *
     * @param parentAsset descriptor of parent asset
     * @param totalElementCount the total number of elements to process.  A negative value is converted to 0.
     * @param maxCacheSize maximum number of elements that should be retrieved from the property server and
     *                     cached in the element list at any one time.  If a number less than one is supplied, 1 is used.
     */
    public AssetLicenses(AssetDescriptor              parentAsset,
                         int                          totalElementCount,
                         int                          maxCacheSize)
    {
        super(parentAsset, totalElementCount, maxCacheSize);
    }


    /**
     * Copy/clone constructor.  Used to reset iterator element pointer to 0;
     *
     * @param parentAsset descriptor of parent asset
     * @param template type-specific iterator to copy; null to create an empty iterator
     */
    public AssetLicenses(AssetDescriptor   parentAsset, AssetLicenses template)
    {
        super(parentAsset, template);
    }


    /**
     * Provides a concrete implementation of cloneElement for the specific iterator type.
     *
     * @param parentAsset descriptor of parent asset
     * @param template object to clone
     * @return new cloned object.
     */
    protected AssetPropertyBase cloneElement(AssetDescriptor  parentAsset, AssetPropertyBase template)
    {
        return new AssetLicense(parentAsset, (AssetLicense)template);
    }


    /**
     * Clones this iterator.
     *
     * @param parentAsset descriptor of parent asset
     * @return new cloned object.
     */
    protected  abstract AssetLicenses cloneIterator(AssetDescriptor  parentAsset);


    /**
     * The iterator can only be used once to step through the elements.  This method returns
     * a boolean to indicate if it has got to the end of the list yet.
     *
     * @return boolean indicating whether there are more elements.
     */
    @Override
    public boolean hasNext()
    {
        return super.pagingIterator.hasNext();
    }


    /**
     * Return the next element in the iteration.
     *
     * @return License next element object that has been cloned.
     */
    @Override
    public AssetLicense next()
    {
        return (AssetLicense)super.pagingIterator.next();
    }


    /**
     * Remove the current element in the iterator. (Null implementation since this iterator works off of cached
     * elements from the property (metadata) server.)
     */
    @Override
    public void remove()
    {
        OCFErrorCode errorCode = OCFErrorCode.UNABLE_TO_REMOVE;
        String       errorMessage = errorCode.getErrorMessageId()
                                  + errorCode.getFormattedErrorMessage(this.getParentAssetTypeName(),
                                                                       this.getParentAssetName(),
                                                                       this.getClass().getName());

        throw new OCFRuntimeException(errorCode.getHTTPErrorCode(),
                                      this.getClass().getName(),
                                      "remove",
                                      errorMessage,
                                      errorCode.getSystemAction(),
                                      errorCode.getUserAction());
    }


    /**
     * Standard toString method.
     *
     * @return print out of variables in a JSON-style
     */
    @Override
    public String toString()
    {
        return "AssetLicenses{" +
                "pagingIterator=" + pagingIterator +
                '}';
    }
}